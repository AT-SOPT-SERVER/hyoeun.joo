package org.sopt.comment.dto.response;

import org.sopt.comment.domain.Comment;

import java.time.LocalDateTime;

public record CommentResponse(Long id, String content, LocalDateTime createdAt,
                              LocalDateTime updatedAt) {
    public static CommentResponse from(Comment comment) {
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }
}
