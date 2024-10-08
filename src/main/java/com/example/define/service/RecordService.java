package com.example.define.service;

import com.example.define.mapper.RecordMapper;
import com.example.define.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    private final RecordMapper recordMapper;

    @Autowired
    private RecordService(RecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    // POST
    public int insertRecord(RecordVo recordVo) {
        return recordMapper.insertRecord(recordVo);
    }

    // GET
    public RecordVo getRecordByUserCodeAndTimestamp(int user_code, String timestamp) {
        return recordMapper.getRecordByUserCodeAndTimestamp(user_code, timestamp);
    }

    public List<RecordVo> getDailyByUserCode(int user_code) {
        return recordMapper.getDailyByUserCode(user_code);
    }

    public List<RecordVo> getStateByUserCodeAndDate(int user_code, String date) {
        return recordMapper.getStateByUserCodeAndDate(user_code, date);
    }

    // UPDATE
    public int updateRecord(RecordVo recordVo) {
        return recordMapper.updateRecord(recordVo);
    }

    // DELETE
    public int deleteRecord(int user_code, String timestamp) {
        return recordMapper.deleteRecord(user_code, timestamp);
    }

}
