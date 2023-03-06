package com.gkritas.privateschoolmanager.user;

import com.gkritas.privateschoolmanager.student.Student;
import com.gkritas.privateschoolmanager.student.StudentRepository;
import com.gkritas.privateschoolmanager.trainer.Trainer;
import com.gkritas.privateschoolmanager.trainer.TrainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TrainerRepository trainerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Student student = studentRepository.findByUsername(username);
        if(student!=null) {
            return new User(student.getUsername(), student.getPassword(), "STUDENT");
        }

        Trainer trainer = trainerRepository.findByUsername(username);
        if(trainer !=null) {
            return new User(trainer.getUsername(), trainer.getPassword(), "TRAINER");
        }

        throw new UsernameNotFoundException("User not found");
    }
}
