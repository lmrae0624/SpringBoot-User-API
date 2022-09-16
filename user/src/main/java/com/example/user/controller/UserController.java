package com.example.user.controller;

import com.example.user.dto.FindUserDto;
import com.example.user.dto.InputUserDto;
import com.example.user.dto.UpdateUserDto;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags={"User Controller"})
@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @PostMapping("")
    public void inputUser(@Valid @RequestBody InputUserDto inputUserDto) throws Exception { //등록
        userService.inputUser(inputUserDto);
    }

    @ApiOperation(value = "회원 목록 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("")
    public List<FindUserDto> findUserAll(){ //전체 조회
        return userService.findUserAll();
    }

    @ApiOperation(value = "회원 상세 조회", notes = "회원 번호를 입력시 해당 회원의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @GetMapping("/{id}")
    public FindUserDto findUserOne(@PathVariable("id") String id){ //개별 조회
        return userService.findUserOne(id);
    }

    @ApiOperation(value = "회원 삭제", notes = "회원 번호를 입력시 해당 회원의 정보를 삭제합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable String id) { //삭제
        userService.deleteUser(id);
    }

    @ApiOperation(value = "회원 수정", notes = "회원 번호를 입력시 해당 회원의 정보를 수정합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @PutMapping("/{id}")
    public FindUserDto modifyUser(@PathVariable String id, @Valid @RequestBody UpdateUserDto updateUserDto) { //수정
        userService.modifyUser(id, updateUserDto);
        return userService.findUserOne(id);
    }
}
