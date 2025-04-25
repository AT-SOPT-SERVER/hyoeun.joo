package org.sopt.mapper;

import org.sopt.domain.Post;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.request.PostUpdateRequest;
import org.sopt.dto.response.PostResponse;

import java.util.List;
import java.util.stream.Collectors;

public class PostMapper {

    //DTO class 사용
    public static Post toEntity(PostRequest request) {
        return new Post(request.getTitle());
    }

    //record 사용 + dto에서 필요한 필드(title)만 가져오는 mapper 역할
    public static Post toEntity(PostUpdateRequest request) {
        return new Post(request.newTitle());
    }

    public static PostResponse toResponse(Post post) {
        return new PostResponse(post.getId(), post.getTitle());
    }

    public static List<PostResponse> toResponseList(List<Post> posts) {
        return posts.stream()
                .map(PostMapper::toResponse)
                .collect(Collectors.toList());
    }
}
