package com.example.schedule_develop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdateScheduleRequest {

    @Size(max = 15, message = "제목은 15자 이하여야 합니다.")
    private String title;

    @Size(max = 100, message = "내용은 100자 이하여야 합니다.")
    private String content;
}
