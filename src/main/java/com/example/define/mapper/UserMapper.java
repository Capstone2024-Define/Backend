package com.example.define.mapper;

import com.example.define.dto.UserResponseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    // POST
    int insertUser(UserResponseDto userInfoVo);

    // GET
    UserResponseDto findUserByKaKaoCode(String kakao_code);

    int existUserByUserCode(int user_code);

    // PUT
}
