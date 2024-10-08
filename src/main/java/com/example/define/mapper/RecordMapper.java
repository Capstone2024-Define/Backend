package com.example.define.mapper;

import com.example.define.vo.DailyVo;
import com.example.define.vo.RecordVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RecordMapper {
    // POST
    int insertRecord(RecordVo recordVo);

    // GET
    RecordVo getRecordByUserCodeAndTimestamp(int user_code, String timestamp);

    List<RecordVo> getDailyByUserCode(int user_code);

    List<RecordVo> getStateByUserCodeAndDate(int user_code, String date);

    // UPDATE
    int updateRecord(RecordVo recordVo);

    // DELETE
    int deleteRecord(int user_code, String timestamp);
}
