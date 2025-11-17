package com.example.schedule_develop.comment.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CommentRequest {

    @NotBlank(message = "내용을 입력해 주세요.")
    @Size(max = 50, message = "내용은 50자 이하여야 합니다.")
    private String content;

    @NotBlank(message = "아이디를 입력해 주세요.")
    private Long userId;
}
