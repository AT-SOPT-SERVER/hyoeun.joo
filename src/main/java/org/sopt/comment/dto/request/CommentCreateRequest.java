package org.sopt.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CommentCreateRequest(
        @Size(max = 300, message = "댓글은 300자 이내여야 합니다.")
        String content
) {
}
