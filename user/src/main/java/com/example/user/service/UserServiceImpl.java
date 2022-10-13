package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.*;
import com.example.user.exception.UserException;
import com.example.user.exception.common.ErrorCode;
import com.example.user.mapper.UserMapper;
import com.example.user.repository.UserRepository;
import com.example.user.response.ResponseUtil;
import com.example.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    // 회원 등록
    public ApiResponse<User> addUser(InputUserRequestDto inputUserRequestDto) {
        // 아이디 중복 검사
        if(userRepository.existsByUsername(inputUserRequestDto.getUsername())){
            throw new UserException(ErrorCode.USERNAME_DUPLICATION);
        }

        User user = UserMapper.instance.inputUserRequestDtoToEntity(inputUserRequestDto);

        return ResponseUtil.success(userRepository.save(user));
    }

    // 회원 전체 조회
    public ApiResponse<List<FindUserResponseDto>> getUserAll(){

        List<FindUserResponseDto> userList = userRepository.findAll()
                .stream()
                .map(FindUserResponseDto::new)
                .collect(Collectors.toList());

        return ResponseUtil.success(userList);
    }

    // 회원 개별 조회
    public ApiResponse<FindUserResponseDto> getUserOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND));

        return ResponseUtil.success(new FindUserResponseDto(user));
    }

    // 회원 존재 유무 체크
    private void userNullCheck(Long id){
        boolean check = userRepository.existsById(id);

        if(!check){
            throw new UserException(ErrorCode.NOT_FOUND);
        }
    }

    // 회원 삭제
    public ApiResponse<String> deleteUser(Long id){
        userNullCheck(id);

        userRepository.deleteById(id);
        return ResponseUtil.success(null);
    }

    // 회원 수정
    public ApiResponse<FindUserResponseDto> modifyUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        userNullCheck(id);

        User user = UserMapper.instance.updateUserRequestDtoToEntity(updateUserRequestDto);

        //userRepository.save(user);
        userRepository.updateUser(id, user);
        return getUserOne(id);
    }


    // 로그인
    public ApiResponse<LoginResponseDto> loginUser(LoginRequestDto loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());
        if(user == null){
            throw new UserException(ErrorCode.NOT_FOUND);
        }

        boolean passwordCheck = passwordEncoder.matches(loginRequest.getPassword(), user.getPassword());
        if(!passwordCheck){
            throw new UserException(ErrorCode.PASSWORD_CHECK);
        }

        String token = jwtTokenProvider.createJwtToken(user);

        return ResponseUtil.success(new LoginResponseDto(token));
    }

}

