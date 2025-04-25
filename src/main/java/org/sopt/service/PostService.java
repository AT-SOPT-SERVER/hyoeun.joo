package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.domain.Post;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.exception.PostErrorMessage;
import org.sopt.mapper.PostMapper;
import org.sopt.repository.PostRepository;
import org.sopt.util.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(final PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void createPost(final PostRequest postRequest) {

        Validator.validateEmpty(postRequest.getTitle());
        Validator.validateMaxLength(postRequest.getTitle());

        if (postRepository.existsByTitle(postRequest.getTitle())) {
            throw new IllegalArgumentException(PostErrorMessage.POST_ALREADY_EXISTS_ERROR.getMessage());
        }

        Post post = PostMapper.toEntity(postRequest);
        postRepository.save(post);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return PostMapper.toResponseList(posts);
    }

    @Transactional(readOnly = true)
    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));
        return PostMapper.toResponse(post);
    }

    @Transactional
    public void updatePost(Long updateId, String newTitle) {
        Post post = postRepository.findById(updateId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        Validator.validateMaxLength(newTitle);
        Validator.validateEmpty(newTitle);

        post.updateTitle(newTitle);
    }


    @Transactional(readOnly = true)
    public List<PostResponse> getPostByKeyword(String keyword) {
        Validator.validateEmpty(keyword);

        List<Post> result = postRepository.findByTitleContaining(keyword);
        if (result.isEmpty()) {
            throw new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_KEYWORD_ERROR.getMessage());
        }

        return PostMapper.toResponseList(result);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        postRepository.delete(post);
    }

}
