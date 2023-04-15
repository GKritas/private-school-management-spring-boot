package com.gkritas.privateschoolmanager.service;

import com.gkritas.privateschoolmanager.exception.UsernameAlreadyExistsException;
import com.gkritas.privateschoolmanager.model.AppUser;
import com.gkritas.privateschoolmanager.model.RegistrationRequest;
import com.gkritas.privateschoolmanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(RegistrationRequest registrationRequest) {
        if (userRepository.findByUsername(registrationRequest.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("User with username " + registrationRequest.getUsername() + " already exists");
        }

        AppUser user = new AppUser();
        user.setUsername(registrationRequest.getUsername());
        user.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        user.setRole("USER");
        userRepository.save(user);
    }
}
