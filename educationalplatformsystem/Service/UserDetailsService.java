package com.example.educationalplatformsystem.Service;


import com.example.educationalplatformsystem.API.ApiException;
import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Rrpository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final AuthRepository authRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authRepository.findUserByUsername(username);

        if (user == null){
            throw new ApiException("Wrong username or password");
        }

        return user;
    }
}
