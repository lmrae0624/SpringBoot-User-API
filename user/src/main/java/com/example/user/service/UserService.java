package com.example.user.service;

import com.example.user.dto.*;

import java.util.List;


public interface UserService {
    ApiResponse addUser(InputUserRequestDto inputUserRequestDto);
    ApiResponse<List<FindUserResponseDto>> getUserAll();
    ApiResponse getUserOne(Long id);
    ApiResponse deleteUser(Long id);
    ApiResponse modifyUser(Long id, UpdateUserRequestDto updateUserRequestDto);
    ApiResponse<LoginResponseDto> loginUser(LoginRequestDto loginRequest);

}

