package org.sopt.service;

import jakarta.persistence.EntityNotFoundException;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.post.PostCreateRequest;
import org.sopt.dto.request.post.PostUpdateRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.exception.PostErrorMessage;
import org.sopt.mapper.PostMapper;
import org.sopt.repository.PostRepository;
import org.sopt.repository.UserRepository;
import org.sopt.util.Validator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(final PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createPost(Long userId, final PostCreateRequest postCreateRequest) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("사용자를 찾을 수 없습니다."));
        Validator.validateEmpty(postCreateRequest.title());
        Validator.validateEmpty(postCreateRequest.content());
        Validator.validateMaxLength(postCreateRequest.title());

        if (postRepository.existsByTitle(postCreateRequest.title())) {
            throw new IllegalArgumentException(PostErrorMessage.POST_ALREADY_EXISTS_ERROR.getMessage());
        }


        Post post = PostMapper.toEntity(postCreateRequest, user);
        postRepository.save(post);

    }

    @Transactional(readOnly = true)
    public List<PostResponse> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return PostMapper.toResponseList(posts);
    }

    @Transactional(readOnly = true)
    public PostDetailResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));
        return PostMapper.toDetailResponse(post);
    }

    @Transactional
    public void updatePost(final Long userId, final Long updateId, final PostUpdateRequest postUpdateRequest) {
        User author = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));
        Post post = postRepository.findById(updateId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        Validator.validateMaxLength(postUpdateRequest.newTitle());
        Validator.validateEmpty(postUpdateRequest.newTitle());

        if (!post.getUser().equals(author)) {
            throw new RuntimeException(PostErrorMessage.POST_UPDATE_FORBIDDEN.getMessage());
        }

        if (postRepository.existsByTitle(postUpdateRequest.newTitle())) {
            throw new IllegalArgumentException(PostErrorMessage.POST_ALREADY_EXISTS_ERROR.getMessage());
        }
        String newTitle = PostMapper.extractNewTitle(postUpdateRequest);
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
    public void deletePost(final Long userId, Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        if (!post.getUser().getId().equals(userId)) {
            throw new RuntimeException(PostErrorMessage.POST_DELETE_FORBIDDEN.getMessage());
        }
        postRepository.delete(post);
    }

}
