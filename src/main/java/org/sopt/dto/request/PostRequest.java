package org.sopt.dto.request;

public class PostRequest {
    private String title;

    public PostRequest() {

    }

    public PostRequest(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

//record 사용시
//public record PostRequest(String title) {
//
//}
