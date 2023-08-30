package com.example.educationalplatformsystem.Rrpository;

import com.example.educationalplatformsystem.Model.Course;
import com.example.educationalplatformsystem.Model.Teacher;
import com.example.educationalplatformsystem.Model.Trainee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    Course findCourseById(Integer id);
    Course findCourseByName(String name);
    List<Course> searchCourseByLevel(String level);
    Course findCourseByNumberOfRegistered(Integer number);

    Course findCourseByIdAndTeacher(Integer id , Teacher teacher);
    Course findCourseByIdAndTrainee(Integer id, Trainee trainee);
    @Query("select c from Course c ORDER BY c.durationInHours asc")
    List<Course> sortCourseByDuration();
   @Query("select max(c.numberOfRegistered) from Course c")
    Integer getHighestNumOfRegistered();

}