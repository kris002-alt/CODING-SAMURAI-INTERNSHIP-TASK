package com.example.AtmSystem.controller;
import com.example.AtmSystem.dto.AuthResponse;
import com.example.AtmSystem.dto.LoginRequest;
import com.example.AtmSystem.dto.UserDTO;
import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.Users;
import com.example.AtmSystem.service.AccountService;
import com.example.AtmSystem.service.JwtService;
import com.example.AtmSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
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
    private JwtService jwtService;
    private Account account;
    @Autowired
    private UserService userService;
    private AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest loginRequest) {
        Users user = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPin());

        String token = jwtService.generateToken(user);
        AuthResponse response = new AuthResponse();
        response.setMessage("Login successful");
        response.setUserId(user.getId());
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<Users> register(@RequestBody @Valid UserDTO userDTO) {
        Users user = userService.createUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}
