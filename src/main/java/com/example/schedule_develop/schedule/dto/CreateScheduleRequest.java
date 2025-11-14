package com.example.schedule_develop.schedule.dto;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String content;
    private Long userId;
}
