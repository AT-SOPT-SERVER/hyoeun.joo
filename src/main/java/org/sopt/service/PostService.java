package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.util.IdGenerator;
import org.sopt.util.Validator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private final PostRepository postRepository = new PostRepository();
    private LocalDateTime latestPostCreatedTime;

    public void createPost(String title) {
        int id = IdGenerator.generatePostId();

        if (latestPostCreatedTime != null && Duration.between(latestPostCreatedTime, LocalDateTime.now()).toMinutes() < 3) {
            throw new IllegalStateException("❌ 도배 방지 : 게시글은 마지막 작성 후 3분이 지나야 작성 가능합니다.");

        }

        Validator.validateEmpty(title);
        Validator.validateMaxLength(title);

        if (postRepository.isExistByTitle(title)) {
            throw new IllegalArgumentException("❌ 이미 존재하는 제목입니다!");
        }

        Post post = new Post(id, title);
        postRepository.save(post);

        latestPostCreatedTime = LocalDateTime.now();
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public boolean updatePost(int updateId, String newTitle) {
        Post post = postRepository.findPostById(updateId);
        Validator.validateMaxLength(newTitle);
        Validator.validateEmpty(newTitle);

        if (post == null) {
            return false;
        }
        post.setTitle(newTitle);
        return true;
    }

    public List<Post> getPostByKeyword(String keyword) {
        Validator.validateEmpty(keyword);
        return postRepository.findPostByKeyWord(keyword);
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }
}
