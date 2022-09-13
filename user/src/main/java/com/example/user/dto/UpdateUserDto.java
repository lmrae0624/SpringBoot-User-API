package com.example.user.dto;

import com.example.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDto {
    private String username; //아이디
    private String password; //비밀번호
    private String name;    //이름
    private String birth;   //생년월일

    public User toEntity() {
        return User.builder()
                .username(username)
                .password(password)
                .name(name)
                .birth(birth)
                .build();
    }
}
