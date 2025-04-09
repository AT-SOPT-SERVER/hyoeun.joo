package org.sopt.util;

public class Validator {
    public static final int MAX_TITLE_LENGTH = 30;

    public static void validateEmpty(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ 제목이 비어있습니다.");
        }
    }

    public static void validateMaxLength(String title) {
        if (title.length() > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("❌ 제목은 " + MAX_TITLE_LENGTH + "자 이하여야 합니다!");
        }
    }
}