package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.UpdateUserDto;
import com.example.user.dto.UserInfoDto;
import com.example.user.dto.UserRegisterDto;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void registerUser(UserRegisterDto userRegisterDto) throws Exception{ //등록
        User user = userRegisterDto.toEntity();

        if(!userRepository.findByUserName(userRegisterDto.getUsername()).isEmpty()){
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        userRepository.registerUser(user);
    }

    public List<UserInfoDto> findUserAll(){ //전체 조회;
        return userRepository.findUserAll();
    }

    public UserInfoDto findUserOne(String id){ //개별 조회
        return new UserInfoDto(userRepository.findUserOne(id));
    }

    public void deleteUser(String id){ //삭제
        userRepository.deleteUser(id);
    }

    public void modifyUser(String id, UpdateUserDto updateUserDto) { //수정
        userRepository.modifyUser(id, updateUserDto);

    }
}

