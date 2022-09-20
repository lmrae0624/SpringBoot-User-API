package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.ApiResponse;
import com.example.user.dto.FindUserResponseDto;
import com.example.user.dto.InputUserRequestDto;
import com.example.user.dto.UpdateUserRequestDto;
import com.example.user.exception.common.ErrorCode;
import com.example.user.repository.UserRepository;
import com.example.user.response.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // 회원 등록
    public ApiResponse inputUser(InputUserRequestDto inputUserRequestDto) {
        User user = inputUserRequestDto.toEntity();

        // 아이디 중복 검사
        if(userRepository.findByUsername(inputUserRequestDto.getUsername())){
            return ResponseUtil.error(ErrorCode.USERNAME_DUPLICATION);
        }
        return ResponseUtil.success(userRepository.save(user));
    }

    // 회원 전체 조회
    public List<FindUserResponseDto> findUserAll(){
        return userRepository.findAll();
    }

    // 회원 개별 조회
    public ApiResponse findUserOne(String id) {
        User user=userRepository.findById(id);

        if(user==null){
            return ResponseUtil.error(ErrorCode.NOT_FOUND);
        }
        return ResponseUtil.success(new FindUserResponseDto(user));
    }

    // 회원 삭제
    public ApiResponse deleteUser(String id){
        User user=userRepository.findById(id);

        if(user==null){
            return ResponseUtil.error(ErrorCode.NOT_FOUND);
        }
        userRepository.deleteById(id);
        return ResponseUtil.success(null);
    }

    // 회원 수정
    public ApiResponse modifyUser(String id, UpdateUserRequestDto updateUserRequestDto) {
        User user=userRepository.findById(id);

        if(user==null){
            return ResponseUtil.error(ErrorCode.NOT_FOUND);
        }
        userRepository.updateUser(id, updateUserRequestDto);
        return findUserOne(id);
    }
}
