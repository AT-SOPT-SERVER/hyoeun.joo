package org.sopt.like.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.sopt.global.exception.PostErrorMessage;
import org.sopt.like.domain.PostLike;
import org.sopt.like.exception.LikeErrorMessage;
import org.sopt.like.repository.PostLikeRepository;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;
import org.sopt.user.domain.User;
import org.sopt.user.exception.UserErrorMessage;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostLikeService {

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public void likePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND.getMessage()));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        if (postLikeRepository.existsByUserAndPost(user, post)) {
            throw new IllegalArgumentException(LikeErrorMessage.ALREADY_LIKE_ERROR.getMessage());
        }

        PostLike postLike = new PostLike(user, post);
        postLikeRepository.save(postLike);
    }

    public void deleteLikePost(Long userId, Long postId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND.getMessage()));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));

        PostLike postLike = postLikeRepository.findByUserAndPost(user, post)
                .orElseThrow(() -> new EntityNotFoundException(LikeErrorMessage.LIKE_NOT_FOUND.getMessage()));

        postLikeRepository.delete(postLike);
    }
}
