package com.example.define.mapper;

import com.example.define.vo.ImageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ImageMapper {
    // POST
    int insertImageName(ImageVo sxCheckVo);

    // GET
    ImageVo getImageByUserCodeAndDate(int user_code, String date);

    // DELETE
    int deleteImageName(int user_code, String date);
}