package org.sopt.util;

public class IdGenerator {
    private static int postId = 1;

    public static int generatePostId() {
        return postId++;
    }
}
