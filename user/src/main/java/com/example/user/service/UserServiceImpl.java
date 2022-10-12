package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.*;
import com.example.user.exception.UserException;
import com.example.user.exception.common.ErrorCode;
import com.example.user.repository.UserRepository;
import com.example.user.response.ResponseUtil;
import com.example.user.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    // 회원 등록
    public ApiResponse addUser(InputUserRequestDto inputUserRequestDto) {
        // 아이디 중복 검사
        if(userRepository.existsByUsername(inputUserRequestDto.getUsername())){
            throw new UserException(ErrorCode.USERNAME_DUPLICATION);
        }

        //UserMapper.instance.inputUserRequestDtoToEntity(inputUserRequestDto)));
        User user = User.builder()
                .username(inputUserRequestDto.getUsername())
                .password(passwordEncoder.encode(inputUserRequestDto.getPassword())) //password 인코딩
                .name(inputUserRequestDto.getName())
                .birth(inputUserRequestDto.getBirth())
                .role(Collections.singletonList("ROLE_USER"))
                .build();

        return ResponseUtil.success(userRepository.save(user));
    }


    // 회원 전체 조회
    public ApiResponse<List<FindUserResponseDto>> getUserAll(){
        return ResponseUtil.success(userRepository.findAll()
                .stream().map(FindUserResponseDto::new).collect(Collectors.toList()));
    }

    // 회원 개별 조회
    public ApiResponse getUserOne(Long id) {
        FindUserResponseDto user = nullUserCheck(id);
        return ResponseUtil.success(user);
    }

    // 없는 회원 체크
    public FindUserResponseDto nullUserCheck(Long id){
        User user=userRepository.findById(id).orElseThrow(() -> new UserException(ErrorCode.NOT_FOUND));
        return new FindUserResponseDto(user);
    }

    // 회원 삭제
    public ApiResponse deleteUser(Long id){
        nullUserCheck(id);

        userRepository.deleteById(id);
        return ResponseUtil.success(null);
    }

    // 회원 수정
    public ApiResponse modifyUser(Long id, UpdateUserRequestDto updateUserRequestDto) {
        nullUserCheck(id);

        User user= User.builder()
                .password(updateUserRequestDto.getPassword())
                .name(updateUserRequestDto.getName())
                .birth(updateUserRequestDto.getBirth())
                .build();

        //userRepository.save(user);
        userRepository.updateUser(id, user);
        return getUserOne(id);
    }


    // 로그인
    public ApiResponse loginUser(LoginRequestDto loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername());

        if(user == null){
            throw new UserException(ErrorCode.NOT_FOUND);
        }

        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new UserException(ErrorCode.PASSWORD_CHECK);
        }
        String token = jwtTokenProvider.createJwtToken(user); //일치한다면 JWT 토큰을 만들어서 반환해준다.

        return ResponseUtil.success(token);
    }

}
