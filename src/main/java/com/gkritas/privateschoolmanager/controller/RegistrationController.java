package com.gkritas.privateschoolmanager.controller;

import com.gkritas.privateschoolmanager.model.RegistrationRequest;
import com.gkritas.privateschoolmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequest registrationRequest) {
        userService.registerUser(registrationRequest);
        return ResponseEntity.ok(registrationRequest);
    }
}
