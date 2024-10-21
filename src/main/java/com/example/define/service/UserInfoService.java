package com.example.define.service;

import com.example.define.mapper.DailyMapper;
import com.example.define.mapper.UserInfoMapper;
import com.example.define.vo.DailyVo;
import com.example.define.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserInfoService {
    private final UserInfoMapper userInfoMapper;

    @Autowired
    private UserInfoService(UserInfoMapper userInfoMapper) {
        this.userInfoMapper = userInfoMapper;
    }

    // INSERT
    public void insertUserInfo(UserInfoVo userInfoVo) {
        userInfoMapper.insertUserInfo(userInfoVo);
    }

    // SELECT
    public UserInfoVo getUserInfoByUserCode(int user_code) {
        return userInfoMapper.getUserInfoByUserCode(user_code);
    }

    // UPDATE
    public void updateUserInfo(UserInfoVo userInfoVo) {
        userInfoMapper.updateUserInfo(userInfoVo);
    }
}
