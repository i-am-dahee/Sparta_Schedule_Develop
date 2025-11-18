package com.example.schedule_develop.comment.service;

import com.example.schedule_develop.comment.domain.Comment;
import com.example.schedule_develop.comment.domain.CommentRepository;
import com.example.schedule_develop.comment.dto.CreateCommentRequest;
import com.example.schedule_develop.comment.dto.CommentResponse;
import com.example.schedule_develop.comment.dto.UpdateCommentRequest;
import com.example.schedule_develop.schedule.domain.Schedule;
import com.example.schedule_develop.schedule.domain.ScheduleRepository;
import com.example.schedule_develop.user.domain.User;
import com.example.schedule_develop.user.domain.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final ScheduleRepository scheduleRepository;

    // 댓글 생성
    @Transactional
    public CommentResponse save(CreateCommentRequest request) {
        User user = userRepository.findById(request.getUserId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 유저입니다.")
        );
        Schedule schedule = scheduleRepository.findById(request.getScheduleId()).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        Comment comment = new Comment(
                request.getContent(),
                user,
                schedule
        );
        Comment savedComment = commentRepository.save(comment);
        return new CommentResponse(
                savedComment.getId(),
                savedComment.getContent(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt(),
                savedComment.getUser().getId(),
                savedComment.getSchedule().getId()
        );
    }

    // 댓글 전체 조회
    @Transactional(readOnly = true)
    public List<CommentResponse> getAll() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentResponse> dtos = new ArrayList<>();
        for (Comment comment : comments) {
            CommentResponse dto = new CommentResponse(
                    comment.getId(),
                    comment.getContent(),
                    comment.getCreatedAt(),
                    comment.getModifiedAt(),
                    comment.getUser().getId(),
                    comment.getSchedule().getId()
            );
            dtos.add(dto);
        }
        return dtos;
    }

    // 댓글 개별 조회
    @Transactional(readOnly = true)
    public CommentResponse getOne(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 댓글입니다.")
        );
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getUser().getId(),
                comment.getSchedule().getId()
        );
    }

    // 댓글 수정
    @Transactional
    public CommentResponse update(Long commentId, UpdateCommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 댓글입니다.")
        );
        comment.update(
                request.getContent()
        );
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getModifiedAt(),
                comment.getUser().getId(),
                comment.getSchedule().getId()
        );
    }

    // 댓글 삭제
    @Transactional
    public void delete(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 댓글입니다.")
        );
        commentRepository.deleteById(commentId);
    }
}
