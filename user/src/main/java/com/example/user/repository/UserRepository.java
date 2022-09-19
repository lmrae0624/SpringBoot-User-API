package com.example.user.repository;

import com.example.user.domain.User;
import com.example.user.dto.FindUserResponseDto;
import com.example.user.dto.UpdateUserRequestDto;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    private static Map<String, User> users; //회원 정보
    private static Long id; //pk
//    private static LocalDate now; //오늘 날짜

    static { //test용
        users = new HashMap<String,User>();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        try {
            users.put("1", new User(1L,"id1","password1", "lee", formatter.parse("1997-01-26"), formatter.parse("2022-01-27")));
            users.put("2", new User(2L,"id2","password2", "kim", formatter.parse("1997-02-26"), formatter.parse("2022-02-27")));
            users.put("3", new User(3L,"id3","password3", "choi", formatter.parse("1997-03-26"), formatter.parse("2022-03-27")));
            users.put("4", new User(4L,"id4","password4", "han", formatter.parse("1997-04-26"), formatter.parse("2022-04-27")));
            users.put("5", new User(5L,"id5","password5", "park", formatter.parse("1997-05-26"), formatter.parse("2022-05-27")));
            id = 5L;
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }


    public void save(User user){ //등록
        // pk 설정
        user.setId(++id);

        // 회원 가입 날짜 = 오늘 날짜
        user.setRegDate(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));

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

    public List<FindUserResponseDto> findAll(){ //전체 조회
        List<FindUserResponseDto> userList = new ArrayList<FindUserResponseDto>();

        for(User user : users.values()) {
            userList.add(new FindUserResponseDto(user));
        }
        return userList;
    }

    public User findById(String id){ //개별 조회
        return users.get(id);
    }


    public void deleteById(String id){ //삭제
        users.remove(id);
    }


    public void updateUser(String id, UpdateUserRequestDto updateUserRequestDto) { //수정
        if(!(updateUserRequestDto.getPassword()==null || updateUserRequestDto.getPassword().isBlank())) users.get(id).setPassword(updateUserRequestDto.getPassword());
        if(!(updateUserRequestDto.getName()==null || updateUserRequestDto.getName().isBlank())) users.get(id).setName(updateUserRequestDto.getName());
        if(updateUserRequestDto.getBirth()!=null) users.get(id).setBirth(updateUserRequestDto.getBirth());
    }

}
