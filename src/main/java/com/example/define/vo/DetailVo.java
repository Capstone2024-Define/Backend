package com.example.define.vo;

import lombok.Data;
import java.util.Date;

@Data
public class DetailVo {
    private Date date;
    private int userCode;
    private String detail_home;
    private String detail_school;
    private String detail_hospital;
}
