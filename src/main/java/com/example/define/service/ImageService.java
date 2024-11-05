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

    private final AmazonS3 amazonS3;
    private final ImageMapper imageMapper;


    // 이미지 업로드 (여러 건)
    public List<String> uploadImage(List<MultipartFile> multipartFiles, int user_code, String date) {
        // 파일 이름 리스트 생성
        List<String> imageUrlList = new ArrayList<>();

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

                // create URL
                String imageUrl = amazonS3.getUrl(bucket, fileName).toString();
                imageUrlList.add(fileName);

            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드 실패");
            }
        });

        // DB INSERT
        ImageVo imageVo = new ImageVo();
        imageVo.setUser_code(user_code);
        imageVo.setDate(date);
        imageVo.setUrl(imageUrlList);
        imageMapper.insertImageName(imageVo);

        return imageUrlList;
    }

    // 이미지 조회
    public List<String> showImage(int user_code, String date) {
        ImageVo imageVo = imageMapper.getImageByUserCodeAndDate(user_code, date);
        return imageVo != null? imageVo.getUrl() : List.of();
    }

    // 이미지 삭제 (단건)
    /*
    public void deleteImage(String fileName) {

        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }
    
     */

    // 이미지 삭제 (여러 건)
    public void deleteImage(int user_code, String date) {

        ImageVo imageVo = imageMapper.getImageByUserCodeAndDate(user_code, date);
        List<String> imageUrls = imageVo.getUrl();

        if (imageUrls != null) {
            imageUrls.forEach(imageUrl -> {
                String imageName = extractImageNameFromUrl(imageUrl);
                try {
                    amazonS3.deleteObject(new DeleteObjectRequest(bucket, imageName));
                    System.out.println("Deleted file: " + imageName);
                } catch (Exception e) {
                    System.err.println("Error deleting file: " + imageName + " - " + e.getMessage());
                }
            });
        }

        // DB DELETE
        imageMapper.deleteImageName(user_code, date);
    }
    

    // 이름 랜덤으로 설정 (중복 방지!)
    private String createFileName(String originalFileName) {
        return System.currentTimeMillis() + "_" + originalFileName;
    }

    // URL -> image Name
    private String extractImageNameFromUrl(String imageUrl) {
        return imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
    }


    // 파일 형식 체크
    private String getfileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
    }
}