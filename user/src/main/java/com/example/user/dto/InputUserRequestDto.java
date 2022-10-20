package com.example.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
@ApiModel(description = "회원 등록시 필요한 정보입니다.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputUserRequestDto {

    @ApiModelProperty(
            value = "아이디",
            dataType = "String",
            example = "gildong123",
            required = true)
    @NotBlank(message = "아이디를 입력해주세요.")
    @Pattern(regexp = "[0-9a-z]{2,20}", message = "아이디를 영문 소문자, 숫자를 사용하여 2자 이상 20자 이하로 입력해주세요.")
    private String username; //아이디

    @ApiModelProperty(
            value = "비밀번호",
            dataType = "String",
            example = "password123",
            required = true)
    @NotBlank(message = "비밀번호를 입력해주세요.")
    @Pattern(regexp = "[0-9a-z]{8,20}", message = "비밀번호를 영문 소문자, 숫자를 사용하여 8자 이상 20자 이하로 입력해주세요.")
    private String password; //비밀번호

    @ApiModelProperty(
            value = "이름",
            dataType = "String",
            example = "홍길동",
            required = true)
    @NotBlank(message = "이름을 입력해주세요.")
    @Size(min=2, message = "이름을 2자 이상으로 입력해주세요")
    private String name;    //이름

    @ApiModelProperty(
            value = "생년월일",
            dataType = "Timestamp",
            example = "1900-01-01")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Timestamp birth;   //생년월일

}

