package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String username; //아이디
    private String password; //비밀번호
    private String name;    //이름
    private String birth;   //생년월일

}
