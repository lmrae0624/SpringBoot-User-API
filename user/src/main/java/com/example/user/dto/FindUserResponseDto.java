package com.example.user.dto;

import com.example.user.domain.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class FindUserResponseDto {

    @ApiModelProperty(
            value = "PK",
            dataType = "Long",
            example = "1",
            required = true)
    private Long id;    //pk

    @ApiModelProperty(
            value = "아이디",
            dataType = "String",
            example = "gildong123",
            required = true)
    private String username; //아이디

    @ApiModelProperty(
            value = "비밀번호",
            dataType = "String",
            example = "password123",
            required = true)
    private String password; //비밀번호

    @ApiModelProperty(
            value = "이름",
            dataType = "String",
            example = "홍길동",
            required = true)
    private String name;    //이름

    @ApiModelProperty(
            value = "생년월일",
            dataType = "Date",
            example = "2000-01-01 00:00:00")
    private Timestamp birth;   //생년월일

    @ApiModelProperty(
            value = "가입날짜",
            dataType = "Date",
            example = "2000-01-01 00:00:00",
            required = true)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp regDate;   //회원 가입 날짜


    public FindUserResponseDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.regDate = user.getRegDate();
    }
}
