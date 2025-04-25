package org.sopt.dto.response;

public class PostResponse {
    private Long id;
    private String title;

    public PostResponse(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}


