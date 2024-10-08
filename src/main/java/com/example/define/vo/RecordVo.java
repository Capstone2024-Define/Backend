package com.example.define.vo;

import lombok.Data;

@Data
public class RecordVo {
    private int user_code;
    private String timestamp;
    private String location;
    private String contents;
}
