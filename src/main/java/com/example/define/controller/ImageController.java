package com.example.define.controller;

import com.example.define.service.ImageService;
import com.example.define.vo.ImageVo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/image")
public class ImageController {
    private ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    // POST
    @PostMapping("/post")
    public ResponseEntity<?> uploadImage (@RequestParam("multipartFiles") List<MultipartFile> multipartFiles, @RequestParam("user_code") int user_code, @RequestParam("date") String date) {
        imageService.uploadImage(multipartFiles, user_code, date);
        return ResponseEntity.ok(date + " 이미지 업로드 성공");
    }

    // GET
    @GetMapping("/show/{user_code}/{date}")
    public ResponseEntity<List<String>> getImages(
            @PathVariable int user_code, @PathVariable String date) {
        List<String> imageUrls = imageService.showImage(user_code, date);
        return ResponseEntity.ok(imageUrls);
    }

    @GetMapping("/period/{user_code}/{start}/{end}")
    public ResponseEntity<List<ImageVo>> getImagesPeriod(@PathVariable int user_code, @PathVariable String start, @PathVariable String end) {
        List<ImageVo> imageList = imageService.getImageByPeriod(user_code, start, end);
        return ResponseEntity.ok(imageList);
    }

    // DELETE
    // 단건삭제
    @PutMapping("/edit/{user_code}/{date}")
    public ResponseEntity<?> deleteImage(
            @PathVariable int user_code, @PathVariable String date,
            @RequestHeader("url") String url) {
        System.out.println("Received url: " + url); // url 헤더 값 출력
        imageService.deleteImage(user_code, date, url);
        return ResponseEntity.ok(date + " 이미지 리스트 수정 완료");
    }

    // 다건삭제
    @DeleteMapping("/delete/{user_code}/{date}")
    public ResponseEntity<?> deleteImages(@PathVariable int user_code, @PathVariable String date) {
        imageService.deleteImages(user_code, date);
        return ResponseEntity.ok(date + " 이미지 삭제 완료");
    }


}
