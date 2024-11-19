package com.example.define.mapper;

import com.example.define.vo.InfoVo;
import com.example.define.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InfoMapper {
    // GET
    List<InfoVo> getInfo();

    // PUT
    InfoVo updateInfo(int info_index);
}
