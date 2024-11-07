package com.example.define.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class periodRequestDto {
    private int user_code;
    private String start;
    private String end;
}
