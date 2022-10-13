package com.example.user.controller;

import com.example.user.domain.User;
import com.example.user.dto.*;
import com.example.user.service.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User Controller", description ="회원 API Controller")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    // 회원 등록
    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @ApiImplicitParam(name = "inputUserRequestDto", value = "회원 등록 정보", required = true)
    @PostMapping("")
    public ApiResponse<User> addUser(@RequestBody @Valid InputUserRequestDto inputUserRequestDto)  {
        return userServiceImpl.addUser(inputUserRequestDto);
    }

    // 회원 전체 조회
    @ApiOperation(value = "회원 목록 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("")
    public ApiResponse<List<FindUserResponseDto>> getUserAll(){
        return userServiceImpl.getUserAll();
    }

    // 회원 개별 조회
    @ApiOperation(value = "회원 상세 조회", notes = "회원 번호를 입력시 해당 회원의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @GetMapping("/{id}")
    public ApiResponse<FindUserResponseDto> getUserOne(@PathVariable("id") Long id) {
        return userServiceImpl.getUserOne(id);
    }

    // 회원 삭제
    @ApiOperation(value = "회원 삭제", notes = "회원 번호를 입력시 해당 회원의 정보를 삭제합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @DeleteMapping("/{id}")
    public ApiResponse<String> deleteUser(@PathVariable Long id) {
        return userServiceImpl.deleteUser(id);
    }

    // 회원 수정
    @ApiOperation(value = "회원 수정", notes = "회원 번호를 입력시 해당 회원의 정보를 수정합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="회원 번호", required = true),
            @ApiImplicitParam(name = "updateUserRequestDto", value = "회원 수정 정보", required = true)})
    @PutMapping("/{id}")
    public ApiResponse<FindUserResponseDto> modifyUser(@PathVariable Long id, @RequestBody @Valid UpdateUserRequestDto updateUserRequestDto) {
        return userServiceImpl.modifyUser(id, updateUserRequestDto);
    }

    // 로그인
    @ApiOperation(value = "로그인", notes = "회원 아이디와 비밀번호 입력 시 로그인합니다.")
    @ApiImplicitParam(name = "loginRequest", value = "회원 로그인 정보", required = true)
    @PostMapping("/login")
    @Transactional
    public ApiResponse<LoginResponseDto> loginUser(@RequestBody @Valid LoginRequestDto loginRequest) {
        return userServiceImpl.loginUser(loginRequest);
    }

}
