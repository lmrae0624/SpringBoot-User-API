package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.dto.FindUserDto;
import com.example.user.dto.InputUserDto;
import com.example.user.dto.UpdateUserDto;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void inputUser(InputUserDto inputUserDto) throws Exception{ //등록
        User user = inputUserDto.toEntity();

        if(userRepository.findByUsername(inputUserDto.getUsername())){ //아이디 중복 확인
            throw new Exception("이미 존재하는 아이디입니다.");
        }
        userRepository.save(user);
    }

    public List<FindUserDto> findUserAll(){ //전체 조회;
        return userRepository.findAll();
    }

    public FindUserDto findUserOne(String id){ //개별 조회
        return new FindUserDto(userRepository.findById(id));
    }

    public void deleteUser(String id){ //삭제
        userRepository.deleteById(id);
    }

    public void modifyUser(String id, UpdateUserDto updateUserDto) { //수정
        userRepository.updateUser(id, updateUserDto);
    }
}

