package com.example.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
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
}
