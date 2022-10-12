package com.example.user.service;

import com.example.user.dto.*;

import java.util.List;


public interface UserService {
    public ApiResponse addUser(InputUserRequestDto inputUserRequestDto);
    public ApiResponse<List<FindUserResponseDto>> getUserAll();
    public ApiResponse getUserOne(Long id);
    public FindUserResponseDto nullUserCheck(Long id);
    public ApiResponse deleteUser(Long id);
    public ApiResponse modifyUser(Long id, UpdateUserRequestDto updateUserRequestDto);
    public ApiResponse loginUser(LoginRequestDto loginRequest);

}

