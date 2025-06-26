package org.sopt.like.exception;


public enum LikeErrorMessage {

    ALREADY_LIKE_ERROR("이미 좋아요를 눌렀습니다"),
    LIKE_NOT_FOUND("좋아요를 취소할 수 없습니다");


    private final String message;

    LikeErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
