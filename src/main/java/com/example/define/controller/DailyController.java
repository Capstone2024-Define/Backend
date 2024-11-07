package com.example.define.controller;

import com.example.define.dto.periodRequestDto;
import com.example.define.service.DailyService;
import com.example.define.vo.DailyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/daily")
public class DailyController {
    private final DailyService dailyService;

    @Autowired
    public DailyController(DailyService dailyService) {
        this.dailyService = dailyService;
    }

    // POST
    @PostMapping("/post")
    public void insertDaily(@RequestBody DailyVo dailyVo) {
        dailyService.insertDaily(dailyVo);
    }

    // GET
    @GetMapping("/records/{user_code}/{date}")
    public DailyVo showDaily(@PathVariable int user_code, @PathVariable String date) {
        return dailyService.getDailyByUserCodeAndDate(user_code, date);
    }

    @GetMapping("/records/{user_code}")
    public List<DailyVo> showDailyByUserCode(@PathVariable int user_code) {
        return dailyService.getDailyByUserCode(user_code);
    }

    @GetMapping("/state/{user_code}/{month}")
    public List<Map<String, Object>> showDailyByUserCode(@PathVariable int user_code, @PathVariable String month) {
        return dailyService.getStateByUserCodeAndMonth(user_code, month);
    }

    @GetMapping("/period/{user_code}/{start}/{end}")
    public List<DailyVo> periodDaily(@PathVariable int user_code, @PathVariable String start, @PathVariable String end) {
        return dailyService.getDailyByPeriod(user_code, start, end);
    }

    /*
    @GetMapping("/period")
    public List<DailyVo> periodDaily(@RequestBody periodRequestDto periodDto) {
        return dailyService.getDailyByPeriod(periodDto.getUser_code(), periodDto.getStart(), periodDto.getEnd());
    }

     */

    // UPDATE
    @PutMapping("/records/edit")
    public void updateDaily(@RequestBody DailyVo dailyVo) {
        dailyService.updateDaily(dailyVo);
    }

    // DELETE
    @DeleteMapping("/delete/{user_code}/{date}")
    public void deleteDaily(@PathVariable int user_code, @PathVariable String date) {
        dailyService.deleteDaily(user_code, date);
    }

}