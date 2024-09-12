package com.example.define.mapper;

import com.example.define.vo.DetailVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

@Mapper
public interface DetailMapper {

    // POST
    int insertDetail(DetailVo detailVo);

    // GET
    DetailVo getDetailByUserCodeAndDate(int userCode, Date date);
    DetailVo getDetailByDate(Date date);
    DetailVo getDetailByUserCode(int userCode);

}