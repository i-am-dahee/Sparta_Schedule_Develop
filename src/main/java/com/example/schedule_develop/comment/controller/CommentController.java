package com.example.schedule_develop.comment.controller;

import com.example.schedule_develop.comment.dto.CreateCommentRequest;
import com.example.schedule_develop.comment.dto.CommentResponse;
import com.example.schedule_develop.comment.dto.UpdateCommentRequest;
import com.example.schedule_develop.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 댓글 생성
    @PostMapping("/comments")
    public ResponseEntity<CommentResponse> createComment(
            @RequestBody @Valid CreateCommentRequest request
            ) {
        CommentResponse result = commentService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    // 댓글 전체 조회
    @GetMapping("/comments")
    public ResponseEntity<List<CommentResponse>> getAllComments() {
        List<CommentResponse> result = commentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 댓글 개별 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> getComment(
            @PathVariable Long commentId
    ) {
        CommentResponse result = commentService.getOne(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long commentId,
            @RequestBody @Valid UpdateCommentRequest request
    ) {
        CommentResponse result = commentService.update(commentId, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(
            @PathVariable Long commentId
    ) {
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
