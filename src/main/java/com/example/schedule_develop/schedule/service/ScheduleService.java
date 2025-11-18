package com.example.schedule_develop.schedule.service;

import com.example.schedule_develop.comment.domain.CommentRepository;
import com.example.schedule_develop.schedule.domain.Schedule;
import com.example.schedule_develop.schedule.domain.ScheduleRepository;
import com.example.schedule_develop.schedule.dto.CreateScheduleRequest;
import com.example.schedule_develop.schedule.dto.PageScheduleResponse;
import com.example.schedule_develop.schedule.dto.ScheduleResponse;
import com.example.schedule_develop.schedule.dto.UpdateScheduleRequest;
import com.example.schedule_develop.user.domain.User;
import com.example.schedule_develop.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    // 일정 생성
    @Transactional
    public ScheduleResponse save(CreateScheduleRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                user
        );
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getUser().getId(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    // 일정 전체 조회
    @Transactional(readOnly = true)
    public List<ScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            ScheduleResponse dto = new ScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getUser().getId(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 일정 페이징 조회
    @Transactional(readOnly = true)
    public Page<PageScheduleResponse> getPages(Pageable pageable) {
        Page<Schedule> schedules = scheduleRepository.findAll(pageable);
        List<PageScheduleResponse> dtos = schedules.getContent().stream()
                .map(schedule -> new PageScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        commentRepository.countByScheduleId(schedule.getId()),
                        schedule.getCreatedAt(),
                        schedule.getModifiedAt(),
                        schedule.getUser().getName()
                ))
                .toList();
        return new PageImpl<>(dtos, pageable, schedules.getTotalElements());
    }


    // 일정 개별 조회
    @Transactional(readOnly = true)
    public ScheduleResponse getOne(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 수정
    @Transactional
    public ScheduleResponse update(UpdateScheduleRequest request, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        schedule.update(
                request.getTitle(),
                request.getContent()
        );
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getUser().getId(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    // 일정 삭제
    @Transactional
    public void delete(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        scheduleRepository.deleteById(scheduleId);
    }
}