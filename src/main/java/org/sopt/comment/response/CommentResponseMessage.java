package org.sopt.comment.response;

public enum CommentResponseMessage {

    COMMENT_CREATE_SUCCESS("댓글이 성공적으로 생성되었습니다."),
    COMMENT_UPDATE_SUCCESS("댓글이 수정되었습니다."),
    COMMENT_DELETE_SUCCESS("댓글이 삭제되었습니다.");

    private final String message;

    CommentResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}