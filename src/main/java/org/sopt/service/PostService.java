package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.util.IdGenerator;
import org.sopt.util.Validator;

import java.util.List;

//서비스는 레포지토리를 사용함 (2_
//서비스는 비지니스 로직에 대한 정보를 담아있어야 됨.
//사용자의 요청에 따라서 DB에 접근하여 데이터를 추가/삭제/수정을 요청을 처리할 수 있어야 함
//여러 도메인 계층을 조율하는 역할을 함

public class PostService {
    private final PostRepository postRepository = new PostRepository();

    public void createPost(String title) {
        int id = IdGenerator.generatePostId();

        Validator.validateEmpty(title);
        Validator.validateMaxLength(title);

        if (postRepository.isExistByTitle(title)) {
            throw new IllegalArgumentException("❌ 이미 존재하는 제목입니다!");
        }

        Post post = new Post(id, title);
        postRepository.save(post);
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
        if (post == null) {
            return false;
        }
        post.setTitle(newTitle);
        return true;
    }

    public boolean deletePostById(int id) {
        return postRepository.delete(id);
    }
}
