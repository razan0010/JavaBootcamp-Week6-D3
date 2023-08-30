package com.example.educationalplatformsystem.Service;


import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Rrpository.AuthRepository;
import com.example.educationalplatformsystem.Rrpository.TeacherRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final TeacherRepository teacherRepository;

    public List<User> getAllUsers(){
        return authRepository.findAll();
    }

    public void register(@Valid User user){

        String hash = new BCryptPasswordEncoder().encode(user.getPassword());

        user.setPassword(hash);
        user.setRole("ADMIN");
        authRepository.save(user);
    }

}