package com.example.define.vo;

import lombok.Data;

import java.util.List;

@Data
public class PrntCheckVo {
    private int user_code;
    private String date;
    // 체크한 항목 -> 문자열배열.
    private List<String> checklist;
}