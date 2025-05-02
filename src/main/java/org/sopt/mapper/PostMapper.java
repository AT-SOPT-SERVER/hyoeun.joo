package org.sopt.mapper;

import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.post.PostCreateRequest;
import org.sopt.dto.request.post.PostUpdateRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostResponse;

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
        return new PostResponse(post.getUser().getName(), post.getTitle());
    }

    public static PostDetailResponse toDetailResponse(Post post) {
        return new PostDetailResponse(post.getUser().getName(),post.getTitle(),post.getContent());
    }

    public static List<PostResponse> toResponseList(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::toResponse)
                .collect(Collectors.toList());
    }
}
