package org.sopt.post.dto.response;

import java.util.List;

public record PostPageResponse<T>(
        List<T> content,
        int totalPages,
        int currentPage,
        long totalElements
) {
}
