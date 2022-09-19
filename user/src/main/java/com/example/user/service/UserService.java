package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.FindUserResponseDto;
import com.example.user.dto.InputUserRequestDto;
import com.example.user.dto.UpdateUserRequestDto;
import com.example.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public void inputUser(InputUserRequestDto inputUserRequestDto) { //등록
        User user = inputUserRequestDto.toEntity();

//        if(userRepository.findByUsername(inputUserRequestDto.getUsername())){
//            throw new UsernameDuplicateException("이미 존재하는 아이디입니다.");
//        }
        userRepository.save(user);
    }

    public List<FindUserResponseDto> findUserAll(){ //전체 조회;
        return userRepository.findAll();
    }


    public FindUserResponseDto findUserOne(String id) { //개별 조회
        User user=userRepository.findById(id);
//        if(user==null){
//            throw new DefaultException("존재하지 않는 회원 번호입니다.");
//        }

        return new FindUserResponseDto(user);
    }

    public void deleteUser(String id){ //삭제
        userRepository.deleteById(id);
    }

    public void modifyUser(String id, UpdateUserRequestDto updateUserRequestDto) { //수정
        userRepository.updateUser(id, updateUserRequestDto);
    }
}
