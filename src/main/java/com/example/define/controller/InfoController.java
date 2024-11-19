package com.example.define.controller;

import com.example.define.service.InfoService;
import com.example.define.service.RecordService;
import com.example.define.vo.InfoVo;
import com.example.define.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/info")
public class InfoController {
    private final InfoService infoService;

    @Autowired
    public InfoController(InfoService infoService) {
        this.infoService = infoService;
    }

    // GET
    @GetMapping("/show")
    public List<InfoVo> showRecord() {
        return infoService.getInfo();
    }

    // PUT
    @PutMapping("/increase")
    public InfoVo updateInfo(@RequestParam int index) {
        return infoService.updateInfo(index);
    }

}
