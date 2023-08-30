package com.example.educationalplatformsystem.Controller;

import com.example.educationalplatformsystem.API.ApiResponse;
import com.example.educationalplatformsystem.Model.Course;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/va/course/")
public class CourseController {

    private final CourseService courseService;

    @GetMapping("get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(200).body(courseService.getAll());
    }


    @PostMapping("add")
    public ResponseEntity addCourse(@AuthenticationPrincipal User user, @RequestBody @Valid Course course){
        courseService.addCourse(user.getId(), course);
        return ResponseEntity.status(200).body( new ApiResponse("Course added"));
    }


    @PutMapping("update")
    public ResponseEntity updateCourse(@AuthenticationPrincipal User user, @RequestBody @Valid Course course){
        courseService.updateCourse(user.getId(), course);
        return ResponseEntity.status(200).body( new ApiResponse("Course updated"));
    }


    @DeleteMapping("delete")
    public ResponseEntity deleteCourse(@AuthenticationPrincipal User user){
        courseService.deleteCourse(user.getId());
        return ResponseEntity.status(200).body( new ApiResponse("Course deleted"));

    }


    @GetMapping("get-by-name/{name}")
    public ResponseEntity findCourseByName(@AuthenticationPrincipal User user, @PathVariable String name){
        return ResponseEntity.status(200).body(courseService.findCourseByName( name));
    }


    @GetMapping("search-by-level/{level}")
    public ResponseEntity searchCourseByLevel(@AuthenticationPrincipal User user, @PathVariable String level){
        return ResponseEntity.status(200).body(courseService.searchCourseByLevel(level));
    }


    @GetMapping("sort-by-duration")
    public ResponseEntity sortCourseByDuration(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(courseService.sortCourseByDuration());
    }


    @GetMapping("highest-registered")
    public ResponseEntity getHighestNumOfRegistered(@AuthenticationPrincipal User user){
        return ResponseEntity.status(200).body(courseService.getHighestNumOfRegistered());
    }
}
