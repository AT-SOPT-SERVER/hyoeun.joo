package org.sopt.controller;

import lombok.extern.slf4j.Slf4j;
import org.sopt.dto.request.comment.CommentCreateRequest;
import org.sopt.dto.request.comment.CommentUpdateRequest;
import org.sopt.dto.response.ResponseDTO;
import org.sopt.dto.response.comment.CommentResponse;
import org.sopt.global.response.PostResponseMessage;
import org.sopt.service.CommentService;
import org.sopt.util.ResponseUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/comments/{postId}")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Void>> createComment(@PathVariable Long postId, @RequestBody CommentCreateRequest commentCreateRequest) {
        commentService.createComment(postId, commentCreateRequest);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_CREATE_SUCCESS.getMessage(), null));
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<ResponseDTO<CommentResponse>> update(@PathVariable Long postId, @PathVariable Long commentId, @RequestBody CommentUpdateRequest commentUpdateRequest) {
        commentService.updateComment(postId, commentId, commentUpdateRequest);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_CREATE_SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDTO<Void>> delete(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);

        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_CREATE_SUCCESS.getMessage(), null));
    }
}
