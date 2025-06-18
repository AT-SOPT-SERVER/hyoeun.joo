package org.sopt.post.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank String newTitle,
        @NotBlank String newContent
) {}
