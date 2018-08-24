package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DataLoader implements CommandLineRunner{
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... strings) throws Exception {
        roleRepository.save(new AppRole("USER"));
        roleRepository.save(new AppRole("ADMIN"));

        AppRole adminRole = roleRepository.findByAppRole("ADMIN");
        AppRole userRole = roleRepository.findByAppRole("USER");

        AppUser appUser = new
                AppUser("bob@bob.com", "mooplays", "Bob", "Bobberson", true, "Mooplays");
        appUser.setRoles(Arrays.asList(userRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

         appUser = new
                 AppUser("jim@jim.com", "Reggie", "Jim", "Jimmerson", true, "Reggie");
        appUser.setRoles(Arrays.asList(userRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

        appUser = new
                AppUser("admin@adm.com", "Maryland11", "Admin", "User", true, "Seanliuboyds2010@gmail.com");
        appUser.setRoles(Arrays.asList(adminRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);

        appUser = new
                AppUser("sam@ev.com", "pass", "Sam", "Everyman", true, "sam");
        appUser.setRoles(Arrays.asList(userRole, adminRole));
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        userRepository.save(appUser);



    }
}
