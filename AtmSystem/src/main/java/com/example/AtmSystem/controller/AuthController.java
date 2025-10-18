package com.example.AtmSystem.controller;
import com.example.AtmSystem.dto.AuthResponse;
import com.example.AtmSystem.dto.LoginRequest;
import com.example.AtmSystem.dto.UserDTO;
import com.example.AtmSystem.models.Users;
import com.example.AtmSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import javax.validation.Valid;
// OR for Spring Boot 3.x:
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        Users user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPin());

        AuthResponse response = new AuthResponse();
        response.setMessage("Login successful");
        response.setUserId(user.getId());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody @Valid UserDTO userDTO) {
        Users user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
