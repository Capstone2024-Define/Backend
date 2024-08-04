package com.example.define.controller;

import com.example.define.Entity.Detail;
import com.example.define.Entity.DetailPK;
import com.example.define.service.DetailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    // POST 로 Detail 추가
    @PostMapping("/detail")
    public ResponseEntity<Detail> insertDetail(@RequestBody Detail detail) {
        Detail saveDetail = detailService.saveDetail(detail);
        return ResponseEntity.ok(saveDetail);
    }


    // 테스트용! GET으로 Detail 전체 조회
    @GetMapping("/detail/alldetail")
    public ResponseEntity selectallDetail(String userCode) {
        List<Detail> allDatail = detailService.allDetail();
        return ResponseEntity.ok(allDatail);
    }


    /*
    // GET으로 특정 Detail 조회
    @GetMapping("/detail/{userCode}")
    public ResponseEntity<Detail> getDetail(@PathVariable int userCode) {
        Optional<Detail> detail = detailService.getDetailByUserCode(userCode);
                return detail.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
     */
}
