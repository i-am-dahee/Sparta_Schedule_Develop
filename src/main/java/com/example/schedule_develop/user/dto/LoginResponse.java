package com.example.schedule_develop.user.dto;

import lombok.Getter;

@Getter
public class LoginResponse {

    public final Long id;
    public final String email;

    public LoginResponse(Long id, String email) {
        this.id = id;
        this.email = email;
    }
}
