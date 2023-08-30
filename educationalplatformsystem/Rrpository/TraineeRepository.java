package com.example.educationalplatformsystem.Rrpository;

import com.example.educationalplatformsystem.Model.Trainee;
import com.example.educationalplatformsystem.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TraineeRepository extends JpaRepository<Trainee, Integer> {

    Trainee findTraineeById(Integer id);
    Trainee findTraineeByUserId(Integer id);
    @Query("select t from Trainee t where t.numberOfCourses >= 10")
    List<Trainee> checkMedal();
    @Query("select t from Trainee t ORDER BY t.numberOfCourses desc")
    List<Trainee> sortByNumOfCourses();


}
