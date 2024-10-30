package com.example.define.controller;

import com.example.define.service.ImageService;
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
    public ResponseEntity<?> uploadImage (@RequestParam("multipartFiles") List<MultipartFile> multipartFiles) {
        imageService.uploadImage(multipartFiles);
        return ResponseEntity.ok("이미지 업로드 성공");
    }

    // DELETE


}
