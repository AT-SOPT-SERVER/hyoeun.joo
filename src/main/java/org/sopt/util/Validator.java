package org.sopt.util;

import java.text.BreakIterator;

public class Validator {
    public static final int MAX_TITLE_LENGTH = 30;

    public static void validateEmpty(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("❌ 입력값이 비어있습니다.");
        }
    }

    public static void validateMaxLength(String title) {
        int length = countGraphemeClusters(title);
        if (length > MAX_TITLE_LENGTH) {
            throw new IllegalArgumentException("❌ 제목은 " + MAX_TITLE_LENGTH + "자 이하여야 합니다!");
        }
    }

    private static int countGraphemeClusters(String text) {
        BreakIterator it = BreakIterator.getCharacterInstance();
        it.setText(text);
        int count = 0;
        while (it.next() != BreakIterator.DONE) {
            count++;
        }
        return count;
    }
}