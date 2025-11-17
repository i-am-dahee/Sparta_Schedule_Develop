package com.example.schedule_develop.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UserRequest {

    @NotBlank(message = "이름을 입력해 주세요.")
    @Size(max = 10, message = "이름은 10자 이하여야 합니다.")
    private String name;

    @NotBlank(message = "이메일을 입력해 주세요.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    private String email;

    @NotBlank(message = "패스워드를 입력해 주세요.")
    @Size(min = 4, max = 10, message = "패스워드는 4자 이상 10자 이하여야 합니다.")
    private String password;
}
