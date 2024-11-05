package com.example.define.vo;

import lombok.Data;

import java.util.List;

@Data
public class ImageVo {
    private int user_code;
    private String date;
    // 이미지 이름 리스트
    private List<String> url;
}