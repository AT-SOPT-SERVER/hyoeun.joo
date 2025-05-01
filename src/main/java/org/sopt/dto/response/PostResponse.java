package org.sopt.dto.response;

public class PostResponse {
    private String authorName;
    private String title;

    public PostResponse(String authorName, String title) {
        this.authorName = authorName;
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getTitle() {
        return title;
    }
}
