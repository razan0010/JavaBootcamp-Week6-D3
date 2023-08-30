package com.example.educationalplatformsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Valid
@AllArgsConstructor
public class TeacherDTO {

    private Integer user_id;

    @NotNull(message = "Name should be not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @Column(columnDefinition = "varchar(12) check( role = 'ADMIN' or role= 'teacher' or role= 'trainee')")
    private String role;

    @NotNull(message = "Courses should be not null")
    @Column(columnDefinition = "varchar(60) not null")
    private String courses;

    @NotNull(message = "Salary should be not null")
    @PositiveOrZero(message =  "salary should be positive")
    @Column(columnDefinition = "double not null")
    private Double salary;


    @Column(columnDefinition =" boolean default false")
    private boolean annualLeave = false;

}
