package com.example.schedule_develop.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    @NotBlank(message = "제목을 입력해 주세요.")
    @Size(max = 15, message = "제모은 15자 이하여야 합니다.")
    private String title;

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max = 100, message = "내용은 100자 이하여야 합니다.")
    private String content;

    @NotBlank(message = "아이디를 입력해 주세요.")
    private Long userId;
}
