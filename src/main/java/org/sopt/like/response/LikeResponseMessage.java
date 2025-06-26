package org.sopt.like.response;

public enum LikeResponseMessage {

    // 게시글
    POST_POST_LIKE_SUCCESS("게시글에 좋아요가 추가되었습니다."),
    POST_DELETE_LIKE_SUCCESS("게시글의 좋아요가 취소되었습니다."),

    // 댓글
    COMMENT_POST_LIKE_SUCCESS("댓글에 좋아요가 추가되었습니다."),
    COMMENT_DELETE_LIKE_SUCCESS("댓글의 좋아요가 취소되었습니다.");

    private final String message;

    LikeResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
