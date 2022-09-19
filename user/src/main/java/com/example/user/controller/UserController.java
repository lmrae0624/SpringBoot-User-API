package com.example.user.controller;

import com.example.user.dto.FindUserResponseDto;
import com.example.user.dto.InputUserRequestDto;
import com.example.user.dto.ResultResponse;
import com.example.user.dto.UpdateUserRequestDto;
import com.example.user.response.Response;
import com.example.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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
    @PostMapping("")
    public void inputUser(@Valid @RequestBody InputUserRequestDto inputUserRequestDto)  { //등록
        userService.inputUser(inputUserRequestDto);
    }

    //    @ApiOperation(value = "회원 목록 전체 조회", notes = "전체 회원 정보를 조회합니다.")
//    @GetMapping("")
//    public List<FindUserResponseDto> findUserAll2(){ //전체 조회
//        return userService.findUserAll();
//    }
    @ApiOperation(value = "회원 목록 전체 조회", notes = "전체 회원 정보를 조회합니다.")
    @GetMapping("")
    public ResultResponse<List<FindUserResponseDto>> findUserAll(){ //전체 조회;
        // List<FindUserResponseDto> users= userService.findUserAll();
//        Map<String,Object> result=new HashMap<>();
//        result.put("data",users);

        return Response.success(userService.findUserAll());
    }

    @ApiOperation(value = "회원 상세 조회", notes = "회원 번호를 입력시 해당 회원의 상세 정보를 조회합니다.")
    @ApiImplicitParam(name="id", value="회원 번호", required = true)
    @GetMapping("/{id}")
    public FindUserResponseDto findUserOne(@PathVariable("id") String id) { //개별 조회
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
    public FindUserResponseDto modifyUser(@PathVariable String id, @Valid @RequestBody UpdateUserRequestDto updateUserRequestDto) { //수정
        userService.modifyUser(id, updateUserRequestDto);
        return userService.findUserOne(id);
    }
}
