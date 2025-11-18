package com.example.schedule_develop.comment.domain;

import com.example.schedule_develop.schedule.domain.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Long countByScheduleId(Long scheduleId);
}
