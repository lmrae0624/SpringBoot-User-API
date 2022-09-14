package com.example.user.dto;

import com.example.user.domain.User;
import lombok.Getter;

@Getter
public class UserInfoDto {
    private String username; //아이디
    private String name;    //이름
    private String birth;   //생년월일

    public UserInfoDto(User user){
        this.username = user.getUsername();
        this.name = user.getName();
        this.birth = user.getBirth();
    }
}
