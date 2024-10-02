package com.example.define.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.define.vo.DailyVo;
import com.example.define.mapper.DailyMapper;

import java.util.Date;
import java.util.List;

@Service
public class DailyService {

    private final DailyMapper dailyMapper;

    @Autowired
    private DailyService(DailyMapper dailyMapper) {
        this.dailyMapper = dailyMapper;
    }

    // INSERT
    public void insertDaily(DailyVo dailyVo) {
        dailyMapper.insertDaily(dailyVo);
    }

    // SELECT
    public DailyVo getDailyByUserCodeAndDate(int user_code, String date) {
        return dailyMapper.getDailyByUserCodeAndDate(user_code, date);
    }

    public List<DailyVo> getDailyByUserCode(int user_code) {
        return dailyMapper.getDailyByUserCode(user_code);
    }


    public List<DailyVo> getStateByUserCodeAndMonth(int user_code, String month) {
        return dailyMapper.getStateByUserCodeAndMonth(user_code, month);
    }

    // DELETE
    public void deleteDaily(int user_code, String date) {
        dailyMapper.deleteDaily(user_code, date);
    }

}
