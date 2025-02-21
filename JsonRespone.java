package com.techzen.academy_n1224.exception;

import com.techzen.academy_n1224.dto.ApiRespone;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsonRespone {
    public static <T>ResponseEntity<ApiRespone<T>> ok(T t) {
        return  ResponseEntity.ok(ApiRespone.<T>builder().data(t).build());
    }

    public static <T> ResponseEntity<ApiRespone<T>> created(T t) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiRespone.<T>builder().data(t).build());
    }

    public static ResponseEntity<Void> noContent() {
        return ResponseEntity.notFound().build();
    }
}
