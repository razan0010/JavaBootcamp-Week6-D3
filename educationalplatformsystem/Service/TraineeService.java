package com.example.educationalplatformsystem.Service;


import com.example.educationalplatformsystem.API.ApiException;
import com.example.educationalplatformsystem.DTO.TraineeDTO;
import com.example.educationalplatformsystem.Model.Trainee;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Rrpository.AuthRepository;
import com.example.educationalplatformsystem.Rrpository.TraineeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TraineeService {

    private final TraineeRepository traineeRepository;
    private final AuthRepository authRepository;

    public List<Trainee> getAll(){
       return traineeRepository.findAll();
    }

    public void addTrainee(Integer user_id, TraineeDTO traineeDTO){

        User user = authRepository.findUserById(user_id);

        if (user == null){
            throw new ApiException("User not found");
        }

        Trainee trainee = new Trainee(traineeDTO.getUser_id(), traineeDTO.getName(), traineeDTO.getMajor(), traineeDTO.getNumberOfCourses(), user,null);

        traineeRepository.save(trainee);
    }

    public void updateTrainee(Integer user_id, TraineeDTO trainee){
        User user = authRepository.findUserById(user_id);
        Trainee trainee1 = traineeRepository.findTraineeByUserId(user.getId());

        if (trainee1 == null) {
            throw new ApiException("Id not found");
        }
        trainee1.setName(trainee.getName());
        trainee1.setMajor(trainee.getMajor());
        trainee1.setNumberOfCourses(trainee.getNumberOfCourses());
        trainee1.setUser(user);

        traineeRepository.save(trainee1);
    }


    public void deleteTrainee(Integer user_id){
        User user = authRepository.findUserById(user_id);
        Trainee trainee = traineeRepository.findTraineeByUserId(user.getId());

        if (trainee == null) {
            throw new ApiException("Id not found");
        }

        authRepository.delete(user);
    }


//    Get trainee data by id
    public Trainee getTraineeById(Integer id){
        Trainee trainee = traineeRepository.findTraineeById(id);

        if (trainee == null) {
            throw new ApiException("Id not found");
        }

        return trainee;
    }


//    Check if the trainee has a medal (if has more than 10 courses)
    public String checkMedal(Integer id){
        Trainee trainee = traineeRepository.findTraineeById(id);

        if (trainee == null) {
            throw new ApiException("Id not found");
        }
        Integer courses = trainee.getNumberOfCourses();
        if (courses >= 10){
            return "The trainee "+ trainee.getName()+ " has medal with "+ courses +" courses";
        } else  return "The trainee "+ trainee.getName()+ " has no medals and has "+ courses +" courses";
    }


//    Get all trainees that have medals
    public List<Trainee> getTraineesWithMedal(){
        if(getAll().isEmpty()){
            throw new ApiException("No Trainees");
        }
        return  traineeRepository.checkMedal();
    }


// Sort trainees by number of courses from max to min
    public List<Trainee> sortByNumOfCourses() {
        if (getAll().isEmpty()) {
            throw new ApiException("No Trainees");
        }
        return traineeRepository.sortByNumOfCourses();
    }

}


