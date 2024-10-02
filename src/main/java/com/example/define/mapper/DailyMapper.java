package com.example.define.mapper;

import com.example.define.vo.DailyVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;


@Mapper
public interface DailyMapper {
    // POST
    int insertDaily(DailyVo dailyVo);

    // GET
    DailyVo getDailyByUserCodeAndDate(int user_code, String date);

    List<DailyVo> getDailyByUserCode(int user_code);

    List<DailyVo> getStateByUserCodeAndMonth(int user_code, String month);

    // DELETE
    int deleteDaily(int user_code, String date);
}
