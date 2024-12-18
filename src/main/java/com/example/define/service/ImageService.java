package com.example.define.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.example.define.mapper.ImageMapper;
import com.example.define.vo.ImageVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor    // 생성자 자동 주입.
public class ImageService {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final String bucketUrl = "https://define-bucket.s3.ap-northeast-2.amazonaws.com/";

    private final AmazonS3 amazonS3;
    private final ImageMapper imageMapper;


    // 이미지 업로드 (여러 건)
    public List<String> uploadImage(List<MultipartFile> multipartFiles, int user_code, String date) {
        // 파일 이름 리스트 생성
        List<String> fileKeyList = new ArrayList<>();

        // 파일 리스트로 넘어온 파일 하나씩 fileNameList에 저장.
        multipartFiles.forEach(file -> {
            // 파일 이름 변경
            String fileName = user_code + "/" + createFileName(file.getOriginalFilename());

            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(file.getSize());
            metadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, metadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));

                fileKeyList.add(fileName);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패");
            }
        });

        // DB INSERT
        ImageVo imageVo = new ImageVo();
        imageVo.setUser_code(user_code);
        imageVo.setDate(date);
        imageVo.setUrl(fileKeyList);
        imageMapper.insertImageName(imageVo);

        return fileKeyList;
    }

    // 이미지 조회
    public List<String> showImage(int user_code, String date) {
        ImageVo imageVo = imageMapper.getImageByUserCodeAndDate(user_code, date);
        List<String> fileKeyList = imageVo != null? imageVo.getUrl() : List.of();

        return fileKeyList.stream().map(fileKey -> bucketUrl + fileKey).toList();
    }

    public List<ImageVo> getImageByPeriod(int user_code, String start, String end) {
        return imageMapper.getImageByPeriod(user_code, start, end);

    }

    // 이미지 삭제 (단건)
    public void deleteImage(int user_code, String date, String url) {
        // 해당 이미지 주소 리스트에서 삭제
        ImageVo updateimageVo = imageMapper.getImageByUserCodeAndDate(user_code, date);
        List<String> imageList = updateimageVo.getUrl();
        imageList.removeIf(imageUrl -> imageUrl.equals(url));

        // 이미지 삭제
        if(url != null) {
            try {
                amazonS3.deleteObject(new DeleteObjectRequest(bucket, url));
                System.out.println("Deleted file: " + url);
            } catch (Exception e) {
                System.err.println("Error deleting file: " + url + " - " + e.getMessage());
            }
        }

        // image DB 수정
        imageMapper.updateUrl(updateimageVo);
    }

    // 이미지 삭제 (여러 건)
    public void deleteImages(int user_code, String date) {

        ImageVo imageVo = imageMapper.getImageByUserCodeAndDate(user_code, date);
        List<String> fileKeyList = imageVo.getUrl();

        if (fileKeyList != null) {
            fileKeyList.forEach(fileKey -> {
                try {
                    amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileKey));
                    System.out.println("Deleted file: " + fileKey);
                } catch (Exception e) {
                    System.err.println("Error deleting file: " + fileKey + " - " + e.getMessage());
                }
            });
        }

        // DB DELETE
        imageMapper.deleteImagesName(user_code, date);
    }
    

    // 이름 랜덤으로 설정 (중복 방지!)
    private String createFileName(String originalFileName) {
        return System.currentTimeMillis() + "_" + originalFileName;
    }
}