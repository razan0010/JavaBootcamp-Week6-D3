package com.example.educationalplatformsystem.Service;


import com.example.educationalplatformsystem.API.ApiException;
import com.example.educationalplatformsystem.DTO.TeacherDTO;
import com.example.educationalplatformsystem.Model.Teacher;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Rrpository.AuthRepository;
import com.example.educationalplatformsystem.Rrpository.TeacherRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final AuthRepository authRepository;

    public List<Teacher> getAll(){
        return teacherRepository.findAll();
    }

    public void addTeacher(Integer user_id, TeacherDTO teacherDTO){
        User user = authRepository.findUserById(user_id);

        if (user == null){
            throw new ApiException("User not found");
        }

        Teacher teacher = new Teacher(teacherDTO.getUser_id(), teacherDTO.getName(), teacherDTO.getCourses(), teacherDTO.getSalary(),teacherDTO.isAnnualLeave(), user,null);

        user.setRole("teacher");
        teacher.setUser(user);
        teacherRepository.save(teacher);
    }


    public void updateTeacher(Integer user_id, TeacherDTO teacherDTO){
        User user = authRepository.findUserById(user_id);
        Teacher teacher1 = teacherRepository.findTeacherByUserId(user.getId());

        if (teacher1 == null) {
            throw new ApiException("User not found");
        }

        teacher1.setName(teacherDTO.getName());
        teacher1.setCourses(teacherDTO.getCourses());
        teacher1.setSalary(teacherDTO.getSalary());
        teacher1.setAnnualLeave(teacherDTO.isAnnualLeave());
        teacher1.setUser(user);

        teacherRepository.save(teacher1);
    }


    public void deleteTeacher(Integer user_id){
        User user = authRepository.findUserById(user_id);
        Teacher teacher = teacherRepository.findTeacherByUserId(user.getId());

        if (teacher == null) {
            throw new ApiException("User not found");
        }

        authRepository.delete(user);
    }


//    Get teacher data by id
    public Teacher getTeacherById(Integer teacher_id){
        Teacher teacher = teacherRepository.getTeacherById(teacher_id);

        if (teacher == null) {
            throw new ApiException("User or Id not found");
        }

        return teacher;
    }


//    Add bonus to salary (Admin can add)
    public void addBonus(Integer id , Double bonus){
        Teacher teacher = teacherRepository.getTeacherById(id);


        if (teacher == null) {
            throw new ApiException("User or Id not found");
        }
        if (bonus <0){
            throw new ApiException("Bonus must be positive number");
        }

        teacher.setSalary(teacher.getSalary()+ bonus);
        teacherRepository.save(teacher);
    }


//    Annual leave request
    public void requestAnnualLeave(Integer user_id){
        User user = authRepository.findUserById(user_id);
        Teacher teacher = teacherRepository.findTeacherByUserId(user.getId());

        if (teacher == null) {
            throw new ApiException("User or Id not found");
        }
        if (teacher.isAnnualLeave()){
            throw new ApiException("The teacher is already on annual leave!");
        }

        teacher.setAnnualLeave(true);
        teacherRepository.save(teacher);
    }
}
