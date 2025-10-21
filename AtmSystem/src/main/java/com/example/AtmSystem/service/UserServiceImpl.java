package com.example.AtmSystem.service;

import com.example.AtmSystem.dto.AccountDTO;
import com.example.AtmSystem.dto.AuthResponse;
import com.example.AtmSystem.dto.UserDTO;
import com.example.AtmSystem.excep.InvalidPinException;
import com.example.AtmSystem.excep.UserAlreadyExistsException;
import com.example.AtmSystem.excep.UserNotFoundException;
import com.example.AtmSystem.models.Account;
import com.example.AtmSystem.models.Users;
import com.example.AtmSystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Users createUser(UserDTO userDTO) {
        // Validate input
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }

        if (userDTO.getPin() == null || !userDTO.getPin().matches("\\d{4}")) {
            throw new IllegalArgumentException("PIN must be exactly 4 digits");
        }

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + userDTO.getUsername() + "' already exists");
        }

        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPin(passwordEncoder.encode(userDTO.getPin()));

        if (userDTO.getAccounts() != null && !userDTO.getAccounts().isEmpty()) {
            for (AccountDTO accountDTO : userDTO.getAccounts()) {
                Account account = new Account();
                account.setAccountNumber(accountDTO.getAccountNumber());
                account.setBalance(accountDTO.getBalance() != null ?
                        BigDecimal.valueOf(accountDTO.getBalance()) : BigDecimal.ZERO);
                account.setAccountType(accountDTO.getAccountType());
                account.setUser(user); // Set the user reference
                user.getAccounts().add(account);
            }
        }

        return userRepository.save(user);
    }


    @Override
    public Users authenticateUser(String username, String pin) {

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(pin, user.getPin())){
            throw new InvalidPinException("Invalid PIN");
        }
        System.out.println("Login successful for: " + username);
        return user;
    }

    @Override
    public void changePin(Long userId, String newPin) {

    }

    @Override
    public Users getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }

    // Other methods...
}