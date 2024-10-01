package com.example.define.vo;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfoVo {
    private int user_code;
    private String user_name;
    private String child_name;
    private Date birth;
    private char sex;
}
