package com.example.define.controller;

import com.example.define.service.PrntCheckService;
import com.example.define.service.SxCheckService;
import com.example.define.vo.PrntCheckVo;
import com.example.define.vo.SxCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sx")
public class SxCheckController {

    private SxCheckService sxCheckService;

    @Autowired
    public SxCheckController(SxCheckService sxCheckService) {
        this.sxCheckService = sxCheckService;
    }

    // POST
    @PostMapping("/post")
    public void insertSx(@RequestBody SxCheckVo sxCheckVo) {
        sxCheckService.insertSx(sxCheckVo);
    }

    // GET
    @GetMapping("/list/{user_code}/{date}")
    public SxCheckVo showSx(@PathVariable int user_code, @PathVariable String date) {
        return sxCheckService.showSx(user_code, date);
    }

    @GetMapping("/frequency/{user_code}/{month}")
    public List<Map<String, Object>> frequencySx(@PathVariable int user_code, @PathVariable String month) {
        return sxCheckService.countSx(user_code, month);
    }

    // DELETE
    @DeleteMapping("delete/{user_code}/{date}")
    public int deletePrntCheck(@PathVariable int user_code, @PathVariable String date) {
        return sxCheckService.deleteSx(user_code, date);
    }
}