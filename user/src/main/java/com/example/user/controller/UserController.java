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

    @PostMapping("/user")
    public User registerUser(@RequestBody User user) { //등록
        return userService.registerUser(user);
    }

    @GetMapping("/user")
    public List<User> findUserAll(){ //전체 조회
        return userService.findUserAll();
    }

    @GetMapping("/user/{id}")
    public User findUserOne(@PathVariable("id") String id){ //개별 조회
        return userService.findUserOne(id);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable String id) { //삭제
        userService.deleteUser(id);
    }

    @PutMapping("/user/{id}")
    public User modifyUser(@PathVariable String id, @RequestBody User user) { //수정
        userService.modifyUser(id, user);
        return userService.findUserOne(id);
    }
}

