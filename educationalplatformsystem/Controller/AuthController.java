package com.example.educationalplatformsystem.Controller;


import com.example.educationalplatformsystem.Model.User;
import com.example.educationalplatformsystem.Service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth/")
public class AuthController {

    private final AuthService authService;
    @PostMapping("register")
    public ResponseEntity register(@RequestBody @Valid User user){
        authService.register(user);
        return ResponseEntity.status(200).body("User registered");
    }

    @GetMapping("logout")
    public ResponseEntity logout(){
        return ResponseEntity.status(200).body("Log out");
    }

    @GetMapping("get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(authService.getAllUsers());
    }

}
