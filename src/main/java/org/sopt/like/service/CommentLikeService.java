package org.sopt.like.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.exception.CommentErrorMessage;
import org.sopt.comment.repository.CommentRepository;
import org.sopt.like.domain.CommentLike;
import org.sopt.like.exception.LikeErrorMessage;
import org.sopt.like.repository.CommentLikeRepository;
import org.sopt.user.domain.User;
import org.sopt.user.exception.UserErrorMessage;
import org.sopt.user.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeService {

    private final CommentLikeRepository commentLikeRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CommentLikeService(CommentLikeRepository commentLikeRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.commentLikeRepository = commentLikeRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void likeComment(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND.getMessage()));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage()));

        if (commentLikeRepository.existsByUserAndComment(user, comment)) {
            throw new IllegalArgumentException(LikeErrorMessage.ALREADY_LIKE_ERROR.getMessage());
        }

        CommentLike commentLike = new CommentLike(user, comment);
        commentLikeRepository.save(commentLike);
    }

    @Transactional
    public void deleteLikeComment(Long userId, Long commentId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(UserErrorMessage.USER_NOT_FOUND.getMessage()));

        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage()));

        CommentLike commentLike = commentLikeRepository.findByUserAndComment(user, comment)
                .orElseThrow(() -> new IllegalArgumentException(LikeErrorMessage.LIKE_NOT_FOUND.getMessage()));

        commentLikeRepository.delete(commentLike);
    }
}
