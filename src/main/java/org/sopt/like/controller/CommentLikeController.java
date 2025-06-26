package org.sopt.like.controller;

import org.sopt.global.ResponseDTO;
import org.sopt.like.response.LikeResponseMessage;
import org.sopt.like.service.CommentLikeService;
import org.sopt.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes/comment")
public class CommentLikeController {

    private final CommentLikeService commentLikeService;

    public CommentLikeController(CommentLikeService commentLikeService) {
        this.commentLikeService = commentLikeService;
    }

    @PostMapping("/{commentId}")
    public ResponseEntity<ResponseDTO<Void>> likeComment(@RequestHeader("userId") Long userId, @PathVariable Long commentId) {
        commentLikeService.likeComment(userId, commentId);
        return ResponseEntity.ok(ResponseUtil.success(LikeResponseMessage.COMMENT_POST_LIKE_SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDTO<Void>> deleteLikeComment(@RequestHeader("userId") Long userId, @PathVariable Long commentId) {
        commentLikeService.deleteLikeComment(userId, commentId);
        return ResponseEntity.ok(ResponseUtil.success(LikeResponseMessage.COMMENT_DELETE_LIKE_SUCCESS.getMessage(), null));
    }
}
