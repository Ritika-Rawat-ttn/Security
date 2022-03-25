package com.example.Security.Controller;

import com.example.Security.Enities.User;
import com.example.Security.Security.SecurityService;
import com.example.Security.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class userController {
    @Autowired
    private SecurityService securityService;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/show")
    public String showRegistrationPage() {
        return "registerUser";
    }

    @PostMapping("/register")
    public String register(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        userRepo.save(user);
        return "login";
    }

    @GetMapping("/login")
    public String showLoginPage(){
        return "login";
    }


    @PostMapping("/loginpage")
    public String login(String email, String password) {
        boolean loginResponse = securityService.login(email, password);
        if (loginResponse) {
            return "page";
        }
        return "login";
    }




}



