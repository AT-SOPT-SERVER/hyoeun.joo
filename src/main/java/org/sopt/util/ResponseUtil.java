package org.sopt.util;

import org.sopt.dto.response.ResponseDTO;

public class ResponseUtil {

    public static <T> ResponseDTO<T> success(T data) {
        return new ResponseDTO<>(200, "요청 성공", data);
    }

    public static <T> ResponseDTO<T> success(String message, T data) {
        return new ResponseDTO<>(200, message, data);
    }

    public static ResponseDTO<?> fail(String message) {
        return new ResponseDTO<>(400, message, null);
    }

    public static ResponseDTO<?> error(int status, String message) {
        return new ResponseDTO<>(status, message, null);
    }
}
