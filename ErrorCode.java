package com.techzen.academy_n1224.exception;

import com.techzen.academy_n1224.DPM.Department;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public enum ErrorCode {
//    STUDENT_NOT_EXIT(40401,"Student is not fould", HttpStatus.NOT_FOUND),
//    TEACHER_NOT_EXIT(40401,"Teacher is not exit",HttpStatus.NOT_FOUND),
    EMPLOYEE_NOT_EXIT(40401,"Employee is not exit",HttpStatus.NOT_FOUND),
    DEPARTMENT_NOT_EXIT(40402,"Employee is not exit",HttpStatus.NOT_FOUND);

    int code;
    String message;
    HttpStatus status;
}
