package com.example.define.service;

import com.example.define.mapper.SxCheckMapper;
import com.example.define.vo.SxCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SxCheckService {
    private final SxCheckMapper sxCheckMapper;

    @Autowired
    public SxCheckService(SxCheckMapper sxCheckMapper) {
        this.sxCheckMapper = sxCheckMapper;
    }

    // POST
    public int insertSx(SxCheckVo sxCheckVo) {
        return sxCheckMapper.insertSx(sxCheckVo);
    }

    // GET
    public SxCheckVo showSx(int user_code, String date) {
        return sxCheckMapper.getSxByUserCodeAndDate(user_code, date);
    }

    public List<Map<String, Object>> countSx(int user_code, String month) {
        return sxCheckMapper.getChecklistFrequencyByMonth(user_code, month);
    }


    // DELETE
    public int deleteSx(int user_code, String date) {
        return sxCheckMapper.deleteSx(user_code, date);
    }


}
