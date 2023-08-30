package com.example.educationalplatformsystem.Controller;

import com.example.educationalplatformsystem.API.ApiResponse;
import com.example.educationalplatformsystem.DTO.TraineeDTO;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Service.TraineeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/trainee/")
public class TraineeController {

    private final TraineeService traineeService;

    @GetMapping("get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(traineeService.getAll());
    }


    @PostMapping("add")
    public ResponseEntity addTrainee(@AuthenticationPrincipal User user, @RequestBody @Valid TraineeDTO traineeDTO){
        traineeService.addTrainee(user.getId(), traineeDTO);
        return ResponseEntity.status(200).body( new ApiResponse("Trainee registered"));
    }


    @PutMapping("update")
    public ResponseEntity updateTrainee(@AuthenticationPrincipal User user, @RequestBody @Valid TraineeDTO trainee){
        traineeService.updateTrainee(user.getId(), trainee);
        return ResponseEntity.status(200).body( new ApiResponse("Trainee updated"));
    }


    @DeleteMapping("delete")
    public ResponseEntity deleteTrainee(@AuthenticationPrincipal User user){
        traineeService.deleteTrainee(user.getId());
        return ResponseEntity.status(200).body( new ApiResponse("Trainee deleted"));
    }


    @GetMapping("get-by-id/{id}")
    public ResponseEntity getTraineeById( @PathVariable Integer id){
        return ResponseEntity.status(200).body(traineeService.getTraineeById(id));
    }


    @GetMapping("check-medal/{id}")
    public ResponseEntity checkMedal(@PathVariable Integer id){
        return ResponseEntity.status(200).body(traineeService.checkMedal(id));
    }


    @GetMapping("get-trainees-with-medal")
    public ResponseEntity getTraineesWithMedal(){
        return ResponseEntity.status(200).body(traineeService.getTraineesWithMedal());
    }


    @GetMapping("sort-by-NumOfCourses")
    public ResponseEntity sortByNumOfCourses(){
        return ResponseEntity.status(200).body(traineeService.sortByNumOfCourses());
    }

}
