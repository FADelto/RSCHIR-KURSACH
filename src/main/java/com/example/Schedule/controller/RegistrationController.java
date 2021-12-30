package com.example.Schedule.controller;

import com.example.Schedule.domain.Role;
import com.example.Schedule.domain.User;
import com.example.Schedule.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }
    @PostMapping("/registration")
    public String addUser(User user, Map<String,Object> model){
        User userFromDb = userRepo.findByUsername(user.getUsername());
        if (userFromDb != null){
            model.put("message","User Exists");
            return "registration";
        }

        user.setActive(true);
        if (Objects.equals(user.getUsername(), "admin"))
            user.setRoles(Collections.singleton(Role.ADMIN)); else
            user.setRoles(Collections.singleton(Role.USER));

        if(user.getUsername().isEmpty()
                || user.getUsername().replaceAll(" ", "").equals("")
                || user.getUsername().length() >= 35
                || user.getPassword().isEmpty()
                || user.getPassword().replaceAll(" ", "").equals("")
                || user.getPassword().length() >= 35
        )
        {
            model.put("message","BAD NAME IDIOT");
            return "registration";
        }else
            userRepo.save(user);
        return "redirect:/login";
    }
}
