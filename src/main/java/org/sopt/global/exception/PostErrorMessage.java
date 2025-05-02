package org.sopt.global.exception;

public enum PostErrorMessage {

    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다."),
    POST_ALREADY_EXISTS_ERROR("❌ 이미 존재하는 제목입니다!"),
    POST_NOT_FOUND_ERROR("해당 게시글이 존재하지 않습니다."),
    POST_NOT_FOUND_KEYWORD_ERROR("🔍 해당 키워드로 검색된 게시글이 없습니다.");

    private final String message;

    PostErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
