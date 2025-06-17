package org.sopt.dto.response.comment;

import org.sopt.domain.Comment;

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
