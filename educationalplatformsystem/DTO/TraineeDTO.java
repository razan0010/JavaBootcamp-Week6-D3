package com.example.educationalplatformsystem.DTO;

import jakarta.persistence.Column;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Valid
@AllArgsConstructor
public class TraineeDTO {

    private Integer user_id;

    @NotNull(message = "Name should be not null")
    @Column(columnDefinition = "varchar(20) not null")
    private String name;

    @NotNull(message = "Major should be not null")
    @Column(columnDefinition = "varchar(50) not null")
    private String major;

    @Column(columnDefinition = "varchar(12) check( role = 'ADMIN' or role= 'teacher' or role= 'trainee')")
    private String role;

    @PositiveOrZero(message =  "Number of Courses should be positive")
    @Column(columnDefinition = "int not null")
    private Integer numberOfCourses=0;

}
