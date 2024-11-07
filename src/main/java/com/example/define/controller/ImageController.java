package com.example.define.controller;

import com.example.define.dto.periodRequestDto;
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
    public ResponseEntity<?> uploadImage (@RequestParam("multipartFiles") List<MultipartFile> multipartFiles, int user_code, String date) {
        imageService.uploadImage(multipartFiles, user_code, date);
        return ResponseEntity.ok(date + " 이미지 업로드 성공");
    }

    // GET
    @GetMapping("/show")
    public ResponseEntity<List<String>> getImages(
            @RequestParam int user_code, @RequestParam String date) {
        List<String> imageUrls = imageService.showImage(user_code, date);
        return ResponseEntity.ok(imageUrls);
    }

    @GetMapping("/peroid")
    public ResponseEntity<ImageVo> getImagesPeroid(@RequestBody periodRequestDto periodDto) {
        ImageVo imageVo = imageService.getImageByPeriod(periodDto.getUser_code(), periodDto.getStart(), periodDto.getEnd());
        return ResponseEntity.ok(imageVo);
    }

    // DELETE
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteImage(int user_code, String date) {
        imageService.deleteImage(user_code, date);
        return ResponseEntity.ok(date + " 이미지 삭제 완료");
    }


}
