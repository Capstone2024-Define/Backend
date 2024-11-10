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
    List<ImageVo> getImageByPeriod(int user_code, String start, String end);

    // UPDATE
    int updateUrl(int user_code, String date, List<String> url);

    // DELETE
    int deleteImagesName(int user_code, String date);
}