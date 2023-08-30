package com.example.educationalplatformsystem.Rrpository;

import com.example.educationalplatformsystem.Model.Teacher;
import com.example.educationalplatformsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    Teacher getTeacherById(Integer id);
    Teacher findTeacherByUserAndId(User user , Integer id);
    Teacher findTeacherByUser(User user);
    Teacher findTeacherByUserId(Integer id);

}
