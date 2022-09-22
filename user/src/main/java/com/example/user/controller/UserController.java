package com.example.user.controller;

import com.example.user.dto.ApiResponse;
import com.example.user.dto.FindUserResponseDto;
import com.example.user.dto.InputUserRequestDto;
import com.example.user.dto.UpdateUserRequestDto;
import com.example.user.response.ResponseUtil;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "User Controller", description ="회원 API Controller")
@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "회원 등록", notes = "회원을 등록합니다.")
    @ApiImplicitParam(name = "inputUserRequestDto", value = "회원 등록 정보", required = true)
    @PostMapping("")
    public ApiResponse inputUser(@RequestBody InputUserRequestDto inputUserRequestDto) { //등록
        if (inputUserRequestDto.getPassword() == null) {
            throw new UserException("aaa");
        }
        return inputUserRequestDto.getPassword().orElseThrow(() -> new UserException("aaa"));
    }

    // 회원 전체 조회
    @ApiOperation(value = "회원 목록 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("")
    public ApiResponse<List<FindUserResponseDto>> findUserAll(){
        return ResponseUtil.success(userService.findUserAll());
    }

    // 회원 개별 조회
    @ApiOperation(value = "회원 상세 조회", notes = "회원 번호를 입력시 해당 회원의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @GetMapping("/{id}")
    public ApiResponse findUserOne(@PathVariable("id") String id) {
        return userService.findUserOne(id);
    }

    // 회원 삭제
    @ApiOperation(value = "회원 삭제", notes = "회원 번호를 입력시 해당 회원의 정보를 삭제합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @DeleteMapping("/{id}")
    public ApiResponse deleteUser(@PathVariable String id) {
        return userService.deleteUser(id);
    }

    // 회원 수정
    @ApiOperation(value = "회원 수정", notes = "회원 번호를 입력시 해당 회원의 정보를 수정합니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name="id", value="회원 번호", required = true),
            @ApiImplicitParam(name = "updateUserRequestDto", value = "회원 수정 정보", required = true)})
    @PutMapping("/{id}")
    public ApiResponse modifyUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) {
        return userService.modifyUser(id, updateUserRequestDto);
    }
}

