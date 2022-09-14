package com.example.user.repository;

import com.example.user.domain.User;
import com.example.user.dto.UpdateUserDto;
import com.example.user.dto.UserInfoDto;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    private static Map<String,User> users;
    private static Long id;

    static { //test용
        users = new HashMap<String,User>();

        users.put("1", new User(1L,"id1","password1", "lee", "220726"));
        users.put("2", new User(2L,"id2","password2", "kim", "220726"));
        users.put("3", new User(3L,"id3","password3", "choi", "220726"));
        users.put("4", new User(4L,"id4","password4", "han", "220726"));
        users.put("5", new User(5L,"id5","password5", "park", "220726"));
        id = 5L;
    }

    public void registerUser(User user){ //등록
        id += 1 ; //pk 설정용
        user.setId(id);

        users.put(id.toString(), user);
    }
    public List<String> findByUserName(String username){ //중복 아이디 조회
        return users.entrySet().stream()
                .filter(entry -> Objects.equals(entry.getValue().getUsername(), username))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public List<UserInfoDto> findUserAll(){ //전체 조회
        List<UserInfoDto> userList = new ArrayList<UserInfoDto>();

        for (User user : users.values()) {
            userList.add(new UserInfoDto(user));
        }
        return userList;
    }

    public User findUserOne(String id){ //개별 조회
        return users.get(id);
    }

    public void deleteUser(String id){ //삭제
        users.remove(id);
    }

    public void modifyUser(String id, UpdateUserDto updateUserDto) { //수정
        users.get(id).setPassword(updateUserDto.getPassword());
        users.get(id).setName(updateUserDto.getName());
        users.get(id).setBirth(updateUserDto.getBirth());
    }

}
