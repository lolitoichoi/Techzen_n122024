package com.techzen.academy_n1224.DPM;

import com.techzen.academy_n1224.Employee;
import com.techzen.academy_n1224.dto.ApiRespone;
import com.techzen.academy_n1224.exception.ApiException;
import com.techzen.academy_n1224.exception.ErrorCode;
import com.techzen.academy_n1224.exception.JsonRespone;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    private final List<Department> departments = new ArrayList<>(
            Arrays.asList(
                    new Department(1, "Giam doc"),
                    new Department(2, "Quan li"),
                    new Department(3, "Ke toan")
            )
    );

    @GetMapping
    public ResponseEntity<?> getAllDepartments() {
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(JsonRespone::ok)
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXIT));
    }

    @PostMapping
    public ResponseEntity<?> created(@RequestBody Department department) {
        department.setId((int) (Math.random() * 10000));
        departments.add(department);
        return JsonRespone.created(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody Department department) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    d.setName(department.getName());
                    return JsonRespone.ok(d);
                })
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXIT));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        return departments.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .map(d -> {
                    departments.remove(d);
                    return JsonRespone.noContent();
                })
                .orElseThrow(() -> new ApiException(ErrorCode.DEPARTMENT_NOT_EXIT));
    }
//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateDepartment(@PathVariable("id") int id, @RequestBody Department updatedDepartment) {
//        for (Department department : departments) {
//            if (department.getId() == id) {
//                department.setName(updatedDepartment.getName());
//                return ResponseEntity.ok(ApiRespone.<Department>builder().data(department).build());
//            }
//        }
//        throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIT);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> deleteDepartment(@PathVariable("id") int id) {
//        for (Department department : departments) {
//            if (department.getId() == id) {
//                departments.remove(department);
//                return JsonRespone.noContent();
//            }
//        }
//        throw new ApiException(ErrorCode.DEPARTMENT_NOT_EXIT);
//    }


}
