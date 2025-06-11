package com.safalifter.userservice;

import com.safalifter.userservice.enums.Role;
import com.safalifter.userservice.model.User;
import com.safalifter.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients

public class UserServiceApplication implements CommandLineRunner {
    private final UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    public UserServiceApplication(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = encoder;
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

    @Override
    public void run(String... args) {
       // final String pass = "$2a$10$2529eBq3R6Y41t03Mku2I.2Nh3W0p25lt.s.85mG0kiAvxI4bsAHa";
        String pass1 = passwordEncoder.encode("admin");
        var admin = User.builder()
                .username("admin")
                .email("admin@gmail.com")
                .password(pass1)
                .role(Role.ADMIN).build();
        if (userRepository.findByUsername("admin").isEmpty()) userRepository.save(admin);
    }
}
