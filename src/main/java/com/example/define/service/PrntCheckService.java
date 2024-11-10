package com.example.define.service;

import com.example.define.mapper.PrntCheckMapper;
import com.example.define.vo.PrntCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrntCheckService {
    private final PrntCheckMapper prntCheckMapper;

    @Autowired
    public PrntCheckService(PrntCheckMapper prntCheckMapper) {
        this.prntCheckMapper = prntCheckMapper;
    }

    // POST
    public void insertPrntCheck(PrntCheckVo prntCheckVo) {
        prntCheckMapper.insertParents(prntCheckVo);
    }

    // GET
    public PrntCheckVo showPrntCheck(int user_code, String date) {
        return prntCheckMapper.getParentsByUserCodeAndDate(user_code, date);
    }

    public String recentParents(int user_code) {
        return prntCheckMapper.recentParents(user_code);
    }

    // DELETE
    public void deletePrntCheck(int user_code, String date) {
        prntCheckMapper.deleteParents(user_code, date);
    }


}
