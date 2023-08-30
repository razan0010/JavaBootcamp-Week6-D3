package com.example.educationalplatformsystem.Service;


import com.example.educationalplatformsystem.API.ApiException;
import com.example.educationalplatformsystem.Model.Course;
import com.example.educationalplatformsystem.Model.Teacher;
import com.example.educationalplatformsystem.Model.Trainee;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Rrpository.AuthRepository;
import com.example.educationalplatformsystem.Rrpository.CourseRepository;
import com.example.educationalplatformsystem.Rrpository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final AuthRepository authRepository;
    private final TeacherRepository teacherRepository;
    private final TraineeService traineeService;

//      get all courses
    public List<Course> getAll(){
        return courseRepository.findAll();
    }


//    add new course
    public void addCourse(Integer user_id, Course course){
        User user = authRepository.findUserById(user_id);
        Teacher teacher = teacherRepository.getTeacherById(user.getId());
        Trainee trainee = traineeService.getTraineeById(user.getId());

        if (user.getRole().equals("teacher")) {
            Course course1 = new Course(user_id, course.getName() , course.getLevel(),course.getDurationInHours(), course.getDescription(), teacher.getName(),course.getNumberOfRegistered(),null,teacher);
            course.setTeacher(course1.getTeacher());

            courseRepository.save(course);
        }

        if (user.getRole().equals("trainee")){
            Course course2 = new Course(user_id, course.getName() , course.getLevel(),course.getDurationInHours(), course.getDescription(), teacher.getName(),course.getNumberOfRegistered(),trainee,null);
            course.setTrainee(course2.getTrainee());
            course.setNumberOfRegistered(course.getNumberOfRegistered() + 1);
            course.getTrainee().setNumberOfCourses(trainee.getNumberOfCourses()+1);
            courseRepository.save(course);
        }

        else {
            Course course2 = new Course(user_id, course.getName(), course.getLevel(), course.getDurationInHours(), course.getDescription(), null, course.getNumberOfRegistered(), null, null);
            courseRepository.save(course2);
        }
    }


//    update course
    public void updateCourse(Integer user_id, Course course){
        User user = authRepository.findUserById(user_id);
        Course course1 = courseRepository.findCourseById(user.getId());

        if (course1 == null){
            throw new ApiException("Id not found");
        }

        course1.setName(course.getName());
        course1.setLevel(course.getLevel());
        course1.setDescription(course.getDescription());
        course1.setDurationInHours(course.getDurationInHours());
        course1.setCoursePresenter(course.getCoursePresenter());
        course1.setNumberOfRegistered(course.getNumberOfRegistered());
        course1.setTeacher(course.getTeacher());

        courseRepository.save(course1);
    }


//    delete course
    public void deleteCourse( Integer user_id){
        User user = authRepository.findUserById(user_id);
        Course course = courseRepository.findCourseById(user.getId());

        if (course == null){
            throw new ApiException("Id not found");
        }

        courseRepository.delete(course);
    }


//    get course data by name
    public Course findCourseByName(String name){
        Course course = courseRepository.findCourseByName(name);

        if (course == null){
            throw new ApiException("Name not found");
        }

        return course;
    }


//    Search course by level
    public List<Course> searchCourseByLevel(String level){
        List<Course> course = courseRepository.searchCourseByLevel(level);

        if (course.isEmpty()){
            throw new ApiException("No courses for this level yet");
        }

        return course;
    }


//    Sort courses by duration
    public List<Course> sortCourseByDuration(){
        if(getAll().isEmpty()){
            throw new ApiException("No courses");
        }
        return courseRepository.sortCourseByDuration();
    }


//    get course that has the highest number of registered
    public Course getHighestNumOfRegistered(){
        if(getAll().isEmpty()){
            throw new ApiException("No courses");
        }

        Integer highest = courseRepository.getHighestNumOfRegistered();
        return courseRepository.findCourseByNumberOfRegistered(highest);
    }
}
