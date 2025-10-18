package com.example.AtmSystem.service;

import com.example.AtmSystem.dto.UserDTO;
import com.example.AtmSystem.excep.InvalidPinException;
import com.example.AtmSystem.excep.UserAlreadyExistsException;
import com.example.AtmSystem.excep.UserNotFoundException;
import com.example.AtmSystem.models.Users;
import com.example.AtmSystem.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Users createUser(UserDTO userDTO) {
        // Validate PIN format
        if (userDTO.getPin() == null || !userDTO.getPin().matches("\\d{4}")) {
            throw new InvalidPinException("PIN must be exactly 4 digits");
        }

        if (userRepository.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExistsException("Username '" + userDTO.getUsername() + "' already exists");
        }

        Users user = new Users();
        user.setUsername(userDTO.getUsername());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPin(passwordEncoder.encode(userDTO.getPin()));

        return userRepository.save(user);
    }

    @Override
    public Users authenticateUser(String username, String pin) {
        if (username == null || username.trim().isEmpty()) {
            throw new UserNotFoundException("Username is required");
        }

        if (pin == null || !pin.matches("\\d{4}")) {
            throw new InvalidPinException("PIN must be exactly 4 digits");
        }

        Users user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User with username '" + username + "' not found"));

        if (!passwordEncoder.matches(pin, user.getPin())) {
            throw new InvalidPinException("Invalid PIN for user: " + username);
        }

        return user;
    }

    @Override
    public void changePin(Long userId, String newPin) {
        if (newPin == null || !newPin.matches("\\d{4}")) {
            throw new InvalidPinException("New PIN must be exactly 4 digits");
        }

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));

        user.setPin(passwordEncoder.encode(newPin));
        userRepository.save(user);
    }
}
