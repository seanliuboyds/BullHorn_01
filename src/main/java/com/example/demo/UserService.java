package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public AppUser findByEmail(String email){
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email){
        return userRepository.countByEmail(email);
    }
    public AppUser findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    public void saveUser(AppUser user){
        user.setRoles(Arrays.asList(roleRepository.findByAppRole("USER")));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

    }public void saveAdmin(AppUser user){
        user.setRoles(Arrays.asList(roleRepository.findByAppRole("ADMIN")));
        user.setEnabled(true);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
