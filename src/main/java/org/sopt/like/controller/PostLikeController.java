package org.sopt.like.controller;

import lombok.RequiredArgsConstructor;
import org.sopt.global.ResponseDTO;
import org.sopt.like.response.LikeResponseMessage;
import org.sopt.like.service.PostLikeService;
import org.sopt.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/likes/post")
@RequiredArgsConstructor
public class PostLikeController {

    private final PostLikeService postLikeService;

    @PostMapping("/{postId}")
    public ResponseEntity<ResponseDTO<Void>> likePost(@RequestHeader("userId") Long userId, @PathVariable Long postId) {
        postLikeService.likePost(userId, postId);
        return ResponseEntity.ok(ResponseUtil.success(LikeResponseMessage.POST_POST_LIKE_SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<ResponseDTO<Void>> deleteLikePost(@RequestHeader("userId") Long userId, @PathVariable Long postId) {
        postLikeService.deleteLikePost(userId, postId);
        return ResponseEntity.ok(ResponseUtil.success(LikeResponseMessage.POST_DELETE_LIKE_SUCCESS.getMessage(), null));
    }
}
