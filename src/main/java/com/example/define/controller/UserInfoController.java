package com.example.define.controller;

import com.example.define.service.UserInfoService;
import com.example.define.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/userinfo")
public class UserInfoController {
    private UserInfoService userInfoService;

    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    // POST
    @PostMapping("/post")
    public void insertDaily(@RequestBody UserInfoVo userInfoVo) {
        userInfoService.insertUserInfo(userInfoVo);
    }

    // GET
    @GetMapping("/get/{user_code}")
    public UserInfoVo showUserInfo(@PathVariable int user_code) {
        return userInfoService.getUserInfoByUserCode(user_code);
    }

    @GetMapping("/exist")
    public int existUser(@RequestParam int user_code) {
        return userInfoService.existUser(user_code);
    }




    // PUT
    @PutMapping("/edit")
    public void editUserInfo(@RequestBody UserInfoVo userInfoVo) {
        userInfoService.updateUserInfo(userInfoVo);
    }
}
