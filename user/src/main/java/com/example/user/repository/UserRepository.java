package com.example.user.repository;

import com.example.user.domain.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static Map<String, User> users;

    static { //test용
        users = new HashMap<String,User>();
        users.put("1", new User(1L,"id1","password1", "lee", "220726"));
        users.put("2", new User(2L,"id2","password2", "kim", "220726"));
        users.put("3", new User(3L,"id3","password3", "choi", "220726"));
        users.put("4", new User(4L,"id4","password4", "han", "220726"));
        users.put("5", new User(5L,"id5","password5", "park", "220726"));
    }

    public User registerUser(User user){ //등록
        users.put(Long.toString(user.getId()),user);
        return user;
    }

    public List<User> findUserAll(){ //전체 조회
        return new ArrayList<User>(users.values());
    }

    public User findUserOne(String id){ //개별 조회
        return users.get(id);
    }

    public void deleteUser(String id){ //삭제
        users.remove(id);
    }

    public void modifyUser(String id, User user) { //수정
        users.replace(id,user);
    }

}
