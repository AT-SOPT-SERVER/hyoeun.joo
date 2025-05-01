package org.sopt.dto.request;

public class PostRequest {
    private String title;
    private String content;

    public PostRequest() {

    }

    public PostRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {return content;}
}

//record 사용시
//public record PostRequest(String title) {
//
//}
