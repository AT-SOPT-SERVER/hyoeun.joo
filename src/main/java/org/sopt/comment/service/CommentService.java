package org.sopt.comment.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.comment.domain.Comment;
import org.sopt.post.domain.Post;
import org.sopt.comment.dto.request.CommentCreateRequest;
import org.sopt.comment.dto.request.CommentUpdateRequest;
import org.sopt.comment.repository.CommentRepository;
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
                .orElseThrow(() -> new EntityNotFoundException("해당 게시글이 존재하지 않습니다."));
        Comment comment = new Comment(commentCreateRequest.content(), post);
        commentRepository.save(comment);

    }


    @Transactional
    public void updateComment(Long postId, Long commentId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));
        if (!comment.getPost().getId().equals(postId)) {
            throw new EntityNotFoundException("해당 댓글이 존재하지 않습니다.");
        }
        comment.updateContent(commentUpdateRequest.content());
    }

    @Transactional
    public void deleteComment(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException("해당 댓글이 존재하지 않습니다."));
        if (!comment.getPost().getId().equals(postId)) {
            throw new EntityNotFoundException("해당 댓글이 존재하지 않습니다.");
        }
        commentRepository.delete(comment);
    }

}
