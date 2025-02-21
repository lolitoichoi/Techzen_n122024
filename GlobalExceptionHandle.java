package com.techzen.academy_n1224.exception;

import com.techzen.academy_n1224.dto.ApiRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandle {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<?> handleApiException(ApiException e) {
        ErrorCode errorCode = e.getErrorCode();

        return ResponseEntity.status(errorCode.getStatus()).
        body(ApiRespone.builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build());
    }
}
