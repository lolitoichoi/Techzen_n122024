package com.techzen.academy_n1224;

import com.techzen.academy_n1224.dto.ApiRespone;
import com.techzen.academy_n1224.exception.ApiException;
import com.techzen.academy_n1224.exception.ErrorCode;
import com.techzen.academy_n1224.exception.JsonRespone;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employee")

public class EmployeeController {
    private final List<Employee> employee = new ArrayList<>(
            Arrays.asList(
                    new Employee(UUID.randomUUID(),"Nguễn Văn Tuấn", LocalDate.of(2003,6,13),Gender.Male,1200000.00,"071212312",20),
                    new Employee(UUID.randomUUID(),"Nguễn Văn TÚ", LocalDate.of(2008,10,10),Gender.Male,1200000.00,"071212312",15),
                    new Employee(UUID.randomUUID(),"Nguễn Văn Hưng", LocalDate.of(20011,11,2),Gender.Male,1200000.00,"071312312",05),
                    new Employee(UUID.randomUUID(),"Nguễn Văn Thịnh", LocalDate.of(2019,9,19),Gender.Male,1200000.00,"071312312",10),
                    new Employee(UUID.randomUUID(),"Đỗ Thị Thi Mai", LocalDate.of(2006,8,11),Gender.Female,1200000.00,"012312312",12)
                    )
    );
//    @GetMapping("/{id}")
//    public ResponseEntity<List<Employee>> getALLEmployee() {
//        return ResponseEntity.ok(employee);
//    }

    @GetMapping
    public ResponseEntity   <?> getAll (
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "dobFrom", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dobFrom,
        @RequestParam(value = "dobTo", required = false) @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate dobTo,
        @RequestParam(value = "gender", required = false) Gender gender,
        @RequestParam(value = "salary", required = false) String salary,
        @RequestParam(value = "phone", required = false) String phone,
        @RequestParam(value = "departmentId", required = false) Integer departmentId
    ) {
        List<Employee> filteredEmployees = employee.stream()
                .filter(e -> (name == null || e.getName().toLowerCase().contains(name.toLowerCase())))
                .filter(e -> (dobFrom == null || e.getDob().isBefore(dobFrom)))
                .filter(e -> (dobTo == null || e.getDob().isAfter(dobTo)))
                .filter(e -> (gender == null || e.getGender() == gender))
                .filter(e -> (phone == null || e.getPhone().contains(phone)))
                .filter(e -> (departmentId == null || Objects.equals(e.getDepartmentId(),departmentId)))
                .filter(e -> (dobFrom == null || e.getDob().isBefore(dobFrom)))
                .filter(e -> {
                    if (salary == null) return true;
                    return switch (salary) {
                        case "luong 5 cu" -> e.getSalary() == 5000000;
                        case "luong 5-10 cu" -> e.getSalary() >= 5000000 && e.getSalary() < 10000000;
                        case "luong tren 10 cu" -> e.getSalary() <= 10000000;
                        default -> true;
                    };
                })
                .collect(Collectors.toList());
                return JsonRespone.ok(filteredEmployees);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiRespone<Employee>> getEmployee(@PathVariable("id") UUID id) {
        for (Employee newEmployee : employee) {
            if (newEmployee.getId() == id) {
                return ResponseEntity.ok(ApiRespone.<Employee>builder()
                        .data(newEmployee)
                        .build());
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIT);
        }

    @PostMapping("/employee/add")
    public ResponseEntity  <ApiRespone<Employee>> create(@RequestBody Employee newEmployee) {
        newEmployee.setId(UUID.randomUUID());
        employee.add(newEmployee);
        return ResponseEntity.status(HttpStatus.CREATED).body(ApiRespone.<Employee>builder()
                .code(40401)
                .message("Student not found")
                .build());
    }

    @PutMapping("/employee/upd/id={id}")
    public ResponseEntity<ApiRespone<Employee>> updEmployee(@RequestBody Employee nv,
                                                             @PathVariable("id") int id) {
        for (Employee newEmployee : employee) {
            if (newEmployee.getId().equals(id)) {
                newEmployee.setName(nv.getName());
                newEmployee.setDob(nv.getDob());
                newEmployee.setGender(nv.getGender());
                newEmployee.setSalary(nv.getSalary());
                newEmployee.setPhone(nv.getPhone());
                return ResponseEntity.ok( ApiRespone.<Employee>builder().data(nv).build());
            }
        }
        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIT);
    }

    @DeleteMapping("/employee/del/id={id}")
    public ResponseEntity<ApiRespone<Employee>> deleteNhanvien(@PathVariable("id") int id) {
        for (Employee newEmployee : employee) {
            if (newEmployee.getId().equals(id)) {
                employee.remove(newEmployee);
                return ResponseEntity.ok( ApiRespone.<Employee>builder().data(newEmployee).build());
            }
        }

        throw new ApiException(ErrorCode.EMPLOYEE_NOT_EXIT);
    }


}