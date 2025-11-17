package com.example.schedule_develop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CreateCommentRequest {

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max = 50, message = "내용은 50자 이하여야 합니다.")
    private String content;

    @NotNull(message = "유저 아이디를 입력해 주세요.")
    private Long userId;

    @NotNull(message = "일정 아이디를 입력해 주세요.")
    private Long scheduleId;
}
