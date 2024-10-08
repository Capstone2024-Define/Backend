package com.example.define.mapper;

import com.example.define.vo.DailyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface DailyMapper {
    // POST
    int insertDaily(DailyVo dailyVo);

    // GET
    DailyVo getDailyByUserCodeAndDate(int user_code, String date);

    List<DailyVo> getDailyByUserCode(int user_code);

    List<DailyVo> getStateByUserCodeAndMonth(int user_code, String month);

    // UPDATE
    int updateDaily(DailyVo dailyVo);

    // DELETE
    int deleteDaily(int user_code, String date);
}