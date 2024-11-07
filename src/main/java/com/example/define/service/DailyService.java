package com.example.define.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.define.vo.DailyVo;
import com.example.define.mapper.DailyMapper;

import java.util.List;
import java.util.Map;

@Service
public class DailyService {

    private final DailyMapper dailyMapper;

    //@Autowired 어노테이션 때도 생성자 1개라서 알아서 의존성 주 입해준댑니다.
    @Autowired
    private DailyService(DailyMapper dailyMapper) {
        this.dailyMapper = dailyMapper;
    }

    // POST
    public void insertDaily(DailyVo dailyVo) {
        dailyMapper.insertDaily(dailyVo);
    }

    // GET
    public DailyVo getDailyByUserCodeAndDate(int user_code, String date) {
        return dailyMapper.getDailyByUserCodeAndDate(user_code, date);
    }

    public List<DailyVo> getDailyByUserCode(int user_code) {
        return dailyMapper.getDailyByUserCode(user_code);
    }


    public List<Map<String, Object>> getStateByUserCodeAndMonth(int user_code, String month) {
        return dailyMapper.getStateByUserCodeAndMonth(user_code, month);
    }

    public List<DailyVo> getDailyByPeriod(int user_code, String start, String end) {
        return dailyMapper.getDailyByPeriod(user_code, start, end);
    }

    // PUT
    public void updateDaily(DailyVo dailyVo) {
        dailyMapper.updateDaily(dailyVo);
    }

    // DELETE
    public void deleteDaily(int user_code, String date) {
        dailyMapper.deleteDaily(user_code, date);
    }

}