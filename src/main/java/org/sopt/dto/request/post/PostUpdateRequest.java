package org.sopt.dto.request.post;

import jakarta.validation.constraints.NotBlank;

public record PostUpdateRequest(
        @NotBlank String newTitle,
        @NotBlank String newContent
) {}
