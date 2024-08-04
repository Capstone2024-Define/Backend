package com.example.define.Entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@ToString
@Getter
//@AllArgsConstructor
@NoArgsConstructor
@Table(name = "detail")

@Entity
@IdClass(DetailPK.class)
public class Detail {

    @Id
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date date;

    @Id
    @Column(name = "user_code", nullable = false)
    private int userCode;

    @Column(length = 600)
    private String detail_home;

    @Column(length = 600)
    private String detail_school;

    @Column(length = 600)
    private String detail_hospital;

    // getter and setters : Lombok으로 해결

    // Builder
    @Builder
    public Detail(Date date, int user_code, String detail_home, String detail_school, String detail_hospital) {
        this.date = date;
        this.userCode = user_code;
        this.detail_home = detail_home;
        this.detail_school = detail_school;
        this.detail_hospital = detail_hospital;
    }
}
