package com.example.define.controller;

import com.example.define.service.DailyService;
import com.example.define.vo.DailyVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/daily")
public class DailyController {
    private DailyService dailyService;

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
    public List<DailyVo> showDailyByUserCode(@PathVariable int user_code, @PathVariable String month) {
        return dailyService.getStateByUserCodeAndMonth(user_code, month);
    }

}
