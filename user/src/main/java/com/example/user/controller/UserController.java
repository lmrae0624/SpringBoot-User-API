package com.example.user.controller;

import com.example.user.domain.User;
import com.example.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    //회원 등록
    @PostMapping("/user")
    public User registerUser(@RequestBody User user) { 
        return userService.registerUser(user);
    }

//회원 조회
    @GetMapping("/user")
    public List<User> findUserAll(){ 
        return userService.findUserAll();
    }

//회원 상세 조회
    @GetMapping("/user/{id}")
    public User findUserOne(@PathVariable("id") String id){ 
        return userService.findUserOne(id);
    }

//회원 삭제
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) { 
        userService.deleteUser(id);
    }

//회원 정보 수정
    @PutMapping("/user/{id}")
    public User modifyUser(@PathVariable String id, @RequestBody User user) { 
        userService.modifyUser(id, user);
        return userService.findUserOne(id);
    }
}

