package org.sopt.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.comment.domain.Comment;
import org.sopt.comment.dto.request.CommentCreateRequest;
import org.sopt.comment.dto.request.CommentUpdateRequest;
import org.sopt.comment.exception.CommentErrorMessage;
import org.sopt.comment.repository.CommentRepository;
import org.sopt.global.exception.PostErrorMessage;
import org.sopt.post.domain.Post;
import org.sopt.post.repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void createComment(Long postId, CommentCreateRequest commentCreateRequest) {

        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new EntityNotFoundException(PostErrorMessage.POST_NOT_FOUND_ERROR.getMessage()));
        Comment comment = new Comment(commentCreateRequest.content(), post);
        commentRepository.save(comment);

    }


    @Transactional
    public void updateComment(Long postId, Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage()));
        if (!comment.getPost().getId().equals(postId)) {
            throw new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage());
        }
        comment.updateContent(commentUpdateRequest.content());
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage()));
        if (!comment.getPost().getId().equals(postId)) {
            throw new EntityNotFoundException(CommentErrorMessage.COMMENT_NOT_FOUND_ERROR.getMessage());
        }
        commentRepository.delete(comment);
    }

}
