package org.sopt.controller;

import org.sopt.domain.Post;
import org.sopt.service.PostService;

import java.util.List;

public class PostController {
    //(1)
    //요청이 들어오면 어떤 요청인지 판단한 후 어떤 서비스를 사용할 지 연결해주는 역할을 함
    private final PostService postService = new PostService();

    public void createPost(final String title) {
        postService.createPost(title);
    }

    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    public Post getPostById(int id) {
        return postService.getPostById(id);
    }

    public Boolean updatePostTitle(int id, String newTitle) {
        return postService.updatePost(id, newTitle);
    }

    public boolean deletePostById(int id) {
        return postService.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postService.getPostByKeyword(keyword);
    }

}

