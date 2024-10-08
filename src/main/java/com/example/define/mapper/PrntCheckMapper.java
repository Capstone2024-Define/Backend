package com.example.define.mapper;

import com.example.define.vo.PrntCheckVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrntCheckMapper {
    // POST
    int insertParents(PrntCheckVo prntCheckVo);

    // GET
    PrntCheckVo getParentsByUserCodeAndDate(int user_code, String date);

    // DELETE
    int deleteParents(int user_code, String date);
}