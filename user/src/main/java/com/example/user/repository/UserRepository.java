package com.example.user.repository;

import com.example.user.domain.User;
import com.example.user.dto.FindUserDto;
import com.example.user.dto.UpdateUserDto;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static Map<String, User> users;
    private static Long id;

    static { //test용
        users = new HashMap<String,User>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        try {
            users.put("1", new User(1L,"id1","password1", "lee",  formatter.parse("20220726")));
            users.put("2", new User(2L,"id2","password2", "kim",  formatter.parse("20220726")));
            users.put("3", new User(3L,"id3","password3", "choi",  formatter.parse("20220726")));
            users.put("4", new User(4L,"id4","password4", "han",  formatter.parse("20220726")));
            users.put("5", new User(5L,"id5","password5", "park",  formatter.parse("20220726")));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        id = 5L;
    }


    public void save(User user){ //등록
        id += 1 ; //pk 설정용
        user.setId(id);

        users.put(id.toString(), user);
    }

    public Boolean findByUsername(String username){ //중복 아이디 조회
        for(User user : users.values()) {
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    public List<FindUserDto> findAll(){ //전체 조회
        List<FindUserDto> userList = new ArrayList<FindUserDto>();

        for(User user : users.values()) {
            userList.add(new FindUserDto(user));
        }
        return userList;
    }

    public User findById(String id){ //개별 조회
        return users.get(id);
    }


    public void deleteById(String id){ //삭제
        users.remove(id);
    }


    public void updateUser(String id, UpdateUserDto updateUserDto) { //수정
        if(!(updateUserDto.getPassword()==null || updateUserDto.getPassword().isBlank())) users.get(id).setPassword(updateUserDto.getPassword());
        if(!(updateUserDto.getName()==null || updateUserDto.getName().isBlank())) users.get(id).setName(updateUserDto.getName());
        if(updateUserDto.getBirth()!=null) users.get(id).setBirth(updateUserDto.getBirth());
    }

}
