package com.example.define.Entity;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Setter
@Getter
public class DetailPK implements Serializable{

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private int userCode;

    // Default constructor
    public DetailPK() {}


    // hashCode and equals
    @Override
    public int hashCode() {
        return Objects.hash(date, userCode);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        DetailPK that = (DetailPK) obj;
        return Objects.equals(date, that.date) && Objects.equals(userCode, that.userCode);
    }
}