package org.sopt.user.exception;


public enum UserErrorMessage {

    USER_NOT_FOUND("사용자를 찾을 수 없습니다.");


    private final String message;

    UserErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
