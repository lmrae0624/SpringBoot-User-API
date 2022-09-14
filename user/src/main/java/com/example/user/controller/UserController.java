package com.example.user.controller;

import com.example.user.dto.UpdateUserDto;
import com.example.user.dto.UserInfoDto;
import com.example.user.dto.UserRegisterDto;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
    public void registerUser(@RequestBody UserRegisterDto userRegisterDto) throws Exception { //등록
        userService.registerUser(userRegisterDto);
    }

    @GetMapping("")
    public List<UserInfoDto> findUserAll(){ //전체 조회
        return userService.findUserAll();
    }

    @GetMapping("/{id}")
    public UserInfoDto findUserOne(@PathVariable("id") String id){ //개별 조회
        return userService.findUserOne(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) { //삭제
        userService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public UserInfoDto modifyUser(@PathVariable String id, @RequestBody UpdateUserDto updateUserDto) { //수정
        userService.modifyUser(id, updateUserDto);
        return userService.findUserOne(id);
    }
}

