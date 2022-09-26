package com.example.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id; //pk
    private String username; //아이디
    private String password; //비밀번호
    private String name;    //이름
    private Timestamp birth;     //생년월일
    private Timestamp regDate;   //회원 가입 날짜
}
