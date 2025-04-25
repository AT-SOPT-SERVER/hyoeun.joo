package org.sopt.util;

//얘 대신에 @GeneratedValue를 체크해주면 됨
public class IdGenerator {
    private static int postId = 1;

    public static int generatePostId() {
        return postId++;
    }
}
