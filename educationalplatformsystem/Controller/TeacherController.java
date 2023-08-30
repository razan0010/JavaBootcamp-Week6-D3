package com.example.educationalplatformsystem.Controller;


import com.example.educationalplatformsystem.API.ApiResponse;
import com.example.educationalplatformsystem.DTO.TeacherDTO;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/teacher/")
public class TeacherController {

    private final TeacherService teacherService;


    @GetMapping("get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(teacherService.getAll());
    }


    @PostMapping("add")
    public ResponseEntity addTeacher(@AuthenticationPrincipal User user, @RequestBody @Valid TeacherDTO teacherDTO){
        teacherService.addTeacher(user.getId(), teacherDTO);
        return ResponseEntity.status(200).body( new ApiResponse("Teacher added"));
    }


    @PutMapping("update")
    public ResponseEntity updateTeacher(@AuthenticationPrincipal User user, @RequestBody @Valid TeacherDTO teacher){
        teacherService.updateTeacher(user.getId(), teacher);
        return ResponseEntity.status(200).body( new ApiResponse("Teacher updated"));
    }


    @DeleteMapping("delete")
    public ResponseEntity deleteTeacher(@AuthenticationPrincipal User user){
        teacherService.deleteTeacher(user.getId());
        return ResponseEntity.status(200).body( new ApiResponse("Teacher deleted"));
    }


    @GetMapping("get-by-id/{id}")
    public ResponseEntity getTeacherById(@AuthenticationPrincipal User user, @PathVariable Integer id){
        return ResponseEntity.status(200).body(teacherService.getTeacherById(id));
    }


    @GetMapping("add-bonus/{id}/{bonus}")
    public ResponseEntity addBonus(@PathVariable Integer id , @PathVariable Double bonus){
        teacherService.addBonus(id, bonus);
        return ResponseEntity.status(200).body(new ApiResponse("The bonus added"));
    }


    @GetMapping("requestAnnualLeave")
    public ResponseEntity requestAnnualLeave(@AuthenticationPrincipal User user){
        teacherService.requestAnnualLeave(user.getId());
        return ResponseEntity.status(200).body(new ApiResponse("The leave request has been approved"));
    }

}
