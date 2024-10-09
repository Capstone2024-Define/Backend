package com.example.define.mapper;

import com.example.define.vo.SxCheckVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SxCheckMapper {
    // POST
    int insertSx(SxCheckVo sxCheckVo);

    // GET
    SxCheckVo getSxByUserCodeAndDate(int user_code, String date);

    List<Map<String, Object>> getChecklistFrequencyByMonth(int user_code, @Param("month") String month);

    // DELETE
    int deleteSx(int user_code, String date);
}