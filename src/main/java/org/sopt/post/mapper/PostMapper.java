package org.sopt.post.mapper;

import org.sopt.comment.dto.response.CommentResponse;
import org.sopt.post.domain.Post;
import org.sopt.post.dto.request.PostCreateRequest;
import org.sopt.post.dto.request.PostUpdateRequest;
import org.sopt.post.dto.response.PostDetailResponse;
import org.sopt.post.dto.response.PostResponse;
import org.sopt.user.domain.User;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    //DTO class 사용
    public static Post toEntity(PostCreateRequest request, User user) {
        return new Post(request.title(), request.content(), user);
    }

    //record 사용 + dto에서 필요한 필드(title)만 가져오는 mapper 형태
    public static String extractNewTitle(PostUpdateRequest request) {
        return request.newTitle();
    }


    public static PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getUser().getName(), post.getTitle());
    }
    public static PostResponse from(Post post) {
        return new PostResponse(post.getId(), post.getTitle(), post.getUser().getName());
    }


    public static PostDetailResponse toDetailResponse(Post post) {
        List<CommentResponse> commentResponses = post.getComments().stream()
                .map(CommentResponse::from)
                .collect(Collectors.toList());
        return new PostDetailResponse(post.getUser().getName(), post.getTitle(), post.getContent(), commentResponses);
    }


    public static List<PostResponse> toResponseList(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::toResponse)
                .collect(Collectors.toList());
    }
}
