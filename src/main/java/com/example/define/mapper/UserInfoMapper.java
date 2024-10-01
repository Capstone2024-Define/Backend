package com.example.define.mapper;

import com.example.define.vo.UserInfoVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserInfoMapper {
    // POST
    int insertUserInfo(UserInfoVo userInfoVo);

    // GET
    UserInfoVo getUserInfoByUserCode(int user_code);

}
