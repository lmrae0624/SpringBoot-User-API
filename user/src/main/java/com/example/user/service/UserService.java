package com.example.user.service;

import com.example.user.domain.User;
import com.example.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User registerUser(User user){ //등록
        return userRepository.registerUser(user);
    }

    public List<User> findUserAll(){ //전체 조회
        return userRepository.findUserAll();
    }

    public User findUserOne(String id){ //개별 조회
        return userRepository.findUserOne(id);
    }

    public void deleteUser(String id){ //삭제
        userRepository.deleteUser(id);
    }

    public void modifyUser(String id, User user) { //수정
        userRepository.modifyUser(id, user);

    }
}
