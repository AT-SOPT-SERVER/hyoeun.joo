package org.sopt.comment.exception;


public enum CommentErrorMessage {


    COMMENT_NOT_FOUND_ERROR("해당 댓글이 존재하지 않습니다.");

    private final String message;

    CommentErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
