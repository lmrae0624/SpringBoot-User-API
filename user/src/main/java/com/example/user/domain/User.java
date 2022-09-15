package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id; //pk
    private String username; //아이디
    private String password; //비밀번호
    private String name;    //이름
    private Date birth;   //생년월일

}

