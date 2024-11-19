package com.example.define.service;

import com.example.define.mapper.InfoMapper;
import com.example.define.mapper.RecordMapper;
import com.example.define.vo.InfoVo;
import com.example.define.vo.RecordVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoService {
    private final InfoMapper infoMapper;

    @Autowired
    private InfoService(InfoMapper infoMapper) {
        this.infoMapper = infoMapper;
    }

    // GET
    public List<InfoVo> getInfo() {
        return infoMapper.getInfo();
    }
    // UPDATE
    public InfoVo updateInfo(int info_index) {
        return infoMapper.updateInfo(info_index);
    }

}
