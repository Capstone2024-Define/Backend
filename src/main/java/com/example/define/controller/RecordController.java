package com.example.define.controller;

import com.example.define.service.RecordService;
import com.example.define.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/record")
public class RecordController {
    private final RecordService recordService;

    @Autowired
    public RecordController(RecordService recordService) {
        this.recordService = recordService;
    }

    // POST
    @PostMapping("/post")
    public void insertRecord(@RequestBody RecordVo recordVo) {
        recordService.insertRecord(recordVo);
    }

    // GET
    @GetMapping("/list/{user_code}/{timestamp}")
    public RecordVo showRecord(@PathVariable int user_code, @PathVariable String timestamp) {
        return recordService.getRecordByUserCodeAndTimestamp(user_code, timestamp);
    }

    @GetMapping("/list-up/{user_code}")
    public List<RecordVo> listupRecord(@PathVariable int user_code) {
        return recordService.getDailyByUserCode(user_code);
    }

    @GetMapping("/list-up/{user_code}/{date}")
    public List<RecordVo> listupRecord(@PathVariable int user_code, @PathVariable String date) {
        return recordService.getStateByUserCodeAndDate(user_code, date);
    }

    // PUT
    @PutMapping("/edit")
    public int editRecord(@RequestBody RecordVo recordVo) {
        return recordService.updateRecord(recordVo);
    }

    // DELETE
    @DeleteMapping("/delete/{user_code}/{timestamp}")
    public int deleteRecord(@PathVariable int user_code, @PathVariable String timestamp) {
        return recordService.deleteRecord(user_code, timestamp);
    }

}
