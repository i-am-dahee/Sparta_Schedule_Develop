package com.example.schedule_develop.comment.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final Long userId;
    private final Long scheduleId;

    public CommentResponse(Long id, String content, LocalDateTime createdAt, LocalDateTime updatedAt, Long userId, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userId = userId;
        this.scheduleId = scheduleId;
    }
}
