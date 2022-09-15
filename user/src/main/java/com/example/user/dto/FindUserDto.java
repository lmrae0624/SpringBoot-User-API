package com.example.user.dto;

import com.example.user.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.Date;

@Getter
public class FindUserDto {
    @ApiModelProperty(example = "1")
    private Long id;    //pk
    @ApiModelProperty(example = "gildong123")
    private String username; //아이디
    @ApiModelProperty(example = "password123")
    private String password; //비밀번호
    @ApiModelProperty(example = "홍길동")
    private String name;    //이름
    @ApiModelProperty(example = "20220728")
    private Date birth;   //생년월일

    public FindUserDto(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.name = user.getName();
        this.birth = user.getBirth();
    }
}

