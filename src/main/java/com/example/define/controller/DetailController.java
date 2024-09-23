package com.example.define.controller;

import com.example.define.Entity.Detail;
import com.example.define.Entity.DetailPK;
import com.example.define.vo.DetailVo;
import com.example.define.service.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DetailController {
    private DetailService detailService;
    private DetailPK detailPK;

    @Autowired
    public DetailController(DetailService detailService) {
        this.detailService = detailService;
    }

    // POST : Detail 추가 o
    @PostMapping("/detail/insert")
    public ResponseEntity<Detail> insertDetail(@RequestBody Detail detail) {
        Detail saveDetail = detailService.saveDetail(detail);
        return ResponseEntity.ok(saveDetail);
    }

    // POST : Detail 추가 (Batis) o
    @PostMapping("/detail/insert/batis")
    public void createDetail(@RequestBody DetailVo detailVo) {
        detailService.createDetail(detailVo);
    }


    // (test) GET : Detail 전체 조회 o
    @GetMapping("/detail/alldetail")
    public ResponseEntity selectallDetail(String userCode) {
        List<Detail> allDatail = detailService.allDetail();
        return ResponseEntity.ok(allDatail);
    }

    // GET : 특정 Detail 조회 x
    @GetMapping("/detail/{userCode}")
    public ResponseEntity<Detail> getDetail(@PathVariable int userCode) {
        Optional<Detail> detail = detailService.getDetailByUserCode(userCode);
                return detail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // GET : 특정 Detail 조회 (Batis) x
    @GetMapping("/detail?user_code={userCode}&date={date}")
    public DetailVo showDetail(@PathVariable int userCode, @PathVariable Date date) {
        return detailService.getDetailByUserCodeAndDate(userCode, date);
    }

    @GetMapping("/detail?date={date}")
    public DetailVo showDetailByDate(@PathVariable Date date) {
        return detailService.showDetailByDate(date);
    }

    @GetMapping("/detail?user_code={userCode}")
    public DetailVo showDetailByUserCode(@PathVariable int userCode) {
        return detailService.showDetailByUserCode(userCode);
    }


}
