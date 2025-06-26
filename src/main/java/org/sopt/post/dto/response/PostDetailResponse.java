package org.sopt.post.dto.response;

import org.sopt.comment.dto.response.CommentResponse;

import java.util.List;

public record PostDetailResponse(String authorName, String title, String content, List<CommentResponse> comments) {
}