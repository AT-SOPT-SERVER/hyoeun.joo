package org.sopt.controller;

import org.sopt.dto.request.post.PostCreateRequest;
import org.sopt.dto.request.post.PostUpdateRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.dto.response.ResponseDTO;
import org.sopt.global.response.PostResponseMessage;
import org.sopt.service.PostService;
import org.sopt.util.ResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/post")
@RestController
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Void>> createPost(@RequestHeader("userId") Long userId, @RequestBody final PostCreateRequest postCreateRequest) {
        postService.createPost(userId, postCreateRequest);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_CREATE_SUCCESS.getMessage(), null));
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<PostResponse>>> getAllPosts() {
        List<PostResponse> posts = postService.getAllPosts();
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_GET_ALL_POSTS_SUCCESS.getMessage(), posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO<PostDetailResponse>> getPostById(@PathVariable Long id) {
        PostDetailResponse post = postService.getPostById(id);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_GET_POST_ID_SUCCESS.getMessage(), post));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> updatePostTitle(
            @RequestHeader final Long userId,
            @PathVariable Long id,
            @RequestBody final PostUpdateRequest postUpdateRequest) {

        postService.updatePost(userId, id, postUpdateRequest);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_UPDATE_SUCCESS.getMessage(), null));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDTO<Void>> deletePostById(@RequestHeader final Long userId, @PathVariable Long id) {
        postService.deletePost(userId, id);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_DELETE_SUCCESS.getMessage(), null));
    }

    @GetMapping("/search")
    public ResponseEntity<ResponseDTO<List<PostResponse>>> searchPostsByKeyword(@RequestHeader final Long userId, @RequestParam String keyword) {
        List<PostResponse> result = postService.getPostByKeyword(keyword);
        return ResponseEntity.ok(ResponseUtil.success(PostResponseMessage.POST_SEARCH_SUCCESS.getMessage(), result));
    }

}

