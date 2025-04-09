package org.sopt.util;

public class Validator {

    public static void validateEmptyTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ 입력값이 유효하지 않습니다 : 제목이 비어있습니다.");
        }
    }
}
