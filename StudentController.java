package com.techzen.academy_n1224;

import com.techzen.academy_n1224.dto.ApiRespone;
import com.techzen.academy_n1224.exception.ApiException;
import com.techzen.academy_n1224.exception.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final List<Student> students = new ArrayList<>(
            Arrays.asList(
                    new Student(1,"Tuan",9.0),
                    new Student(2,"Mai",8.0),
                    new Student(3,"Hưng",9.0)
            )
    );
   // @RequestMapping("/student")
    @GetMapping("/{id}")
    public ResponseEntity <ApiRespone<Student>> getById(@PathVariable("id") int id ) {
        for (Student student : students) {
            if (student.getId() == id){
               return ResponseEntity.ok(ApiRespone.<Student>builder()
                       .data(student)
                       .build());
            }
        }
        throw new ApiException(ErrorCode.STUDENT_NOT_EXIT);
//        return ResponseEntity.status(ErrorCode.STUDENT_NOT_EXIT.getStatus()).
//        body(ApiRespone.<Student>builder()
//                .code(ErrorCode.STUDENT_NOT_EXIT.getCode())
//                .message(ErrorCode.STUDENT_NOT_EXIT.getMessage())
//                .build());

    }

//    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @PostMapping  // thêm
    public ResponseEntity  <ApiRespone<Student>> save(@RequestBody Student student) {
        student.setId((int)(Math.random() * 10000));
        students.add(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiRespone.<Student>builder()
                .code(40401)
                .message("Student not found")
                .build());
    }

    @PutMapping("/{id}") // update
    public ResponseEntity  <ApiRespone<Student>> update(@PathVariable int id, @RequestBody Student student) {

        for (Student stud : students) {
            if (stud.getId() == id) {
                stud.setName(student.getName());
                stud.setScore(student.getScore());
                return ResponseEntity.ok(ApiRespone.<Student>builder()
                        .data(student)
                        .build());
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiRespone.<Student>builder()
                .code(40401)
                .message("Student not found")
                .build());
    }
}
