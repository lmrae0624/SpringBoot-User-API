package com.example.user.service;

import com.example.user.dto.*;

import java.util.List;


public interface UserService {
    ApiResponse<FindUserResponseDto> addUser(InputUserRequestDto inputUserRequestDto);
    ApiResponse<List<FindUserResponseDto>> getUserAll();
    ApiResponse<FindUserResponseDto> getUserOne(Long id);
    ApiResponse<String> deleteUser(Long id);
    ApiResponse<FindUserResponseDto> modifyUser(Long id, UpdateUserRequestDto updateUserRequestDto);
    ApiResponse<LoginResponseDto> loginUser(LoginRequestDto loginRequest);


}

