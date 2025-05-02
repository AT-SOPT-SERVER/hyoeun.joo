package org.sopt.global.response;

public enum PostResponseMessage {

    POST_CREATE_SUCCESS("게시글이 성공적으로 생성되었습니다."),
    POST_GET_ALL_POSTS_SUCCESS("게시글 조회에 성공했습니다."),
    POST_UPDATE_SUCCESS("게시글 제목이 수정되었습니다."),
    POST_GET_POST_ID_SUCCESS("특정 ID 게시글 조회에 성공했습니다."),
    POST_DELETE_SUCCESS("게시글이 삭제되었습니다."),
    POST_SEARCH_SUCCESS("게시글 검색에 성공했습니다.");

    private final String message;

    PostResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
