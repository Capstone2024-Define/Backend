package com.example.define.controller;

import com.example.define.service.PrntCheckService;
import com.example.define.vo.PrntCheckVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prnt")
public class PrntCheckController {

    private PrntCheckService prntCheckService;

    @Autowired
    public PrntCheckController(PrntCheckService prntCheckService) {
        this.prntCheckService = prntCheckService;
    }

    // POST
    @PostMapping("/post")
    public void insertPrntCheck(@RequestBody PrntCheckVo prntCheckVo) {
        prntCheckService.insertPrntCheck(prntCheckVo);
    }

    // GET
    @GetMapping("/list/{user_code}/{date}")
    public PrntCheckVo showPrntCheck(@PathVariable int user_code, @PathVariable String date) {
        return prntCheckService.showPrntCheck(user_code, date);
    }

    @GetMapping("/recent/{user_code}")
    PrntCheckVo recentParents(@PathVariable int user_code) {
        return prntCheckService.recentParents(user_code);
    }


    // DELETE
    @DeleteMapping("delete/{user_code}/{date}")
    public void deletePrntCheck(@PathVariable int user_code, @PathVariable String date) {
        prntCheckService.deletePrntCheck(user_code, date);
    }
}