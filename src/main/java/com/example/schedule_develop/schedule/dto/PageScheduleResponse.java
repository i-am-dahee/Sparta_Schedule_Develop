package com.example.schedule_develop.schedule.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PageScheduleResponse {

    private final Long id;
    private final String title;
    private final String content;
    private final Long commentCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String userName;

    public PageScheduleResponse(Long id, String title, String content, Long commentCount, LocalDateTime createdAt, LocalDateTime updatedAt, String userName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.commentCount = commentCount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userName = userName;
    }
}
