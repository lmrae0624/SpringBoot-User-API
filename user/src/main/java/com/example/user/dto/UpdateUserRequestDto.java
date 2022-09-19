package com.example.user.dto;

import com.example.user.domain.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Size;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequestDto {

    @ApiModelProperty(
            value = "비밀번호",
            dataType = "String",
            example = "password123",
            required = true)
    @Size(min=8, message = "비밀번호를 8자 이상 입력해주세요")
    private String password; //비밀번호

    @ApiModelProperty(
            value = "이름",
            dataType = "String",
            example = "홍길동",
            required = true)
    @Size(min=2, message = "이름을 2자 이상으로 입력해주세요")
    private String name;    //이름

    @ApiModelProperty(
            value = "생년월일",
            dataType = "Date",
            example = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;   //생년월일

    public User toEntity() {
        return User.builder()
                .password(password)
                .name(name)
                .birth(birth)
                .build();
    }
}

