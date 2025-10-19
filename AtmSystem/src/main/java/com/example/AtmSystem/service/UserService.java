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

public interface UserService {
    Users createUser(UserDTO userDTO);
    Users authenticateUser(String username, String pin);
    void changePin(Long userId, String newPin);

    Users getUserById(Long userId);
}
