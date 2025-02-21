package com.techzen.academy_n1224;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    private UUID id;
    private String name;
    private LocalDate dob;
    private Gender gender;
    private Double salary;
    private String phone;
    private Integer departmentId;
}