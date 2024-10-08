package com.example.define.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import java.util.Date;


@Data
public class DailyVo {
    private int user_code;
    private String date;
    private String home;
    private String school;
    private String hospital;
    private String summary;
    private int state;
}