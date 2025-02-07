package com.auth.controller;

import com.auth.repository.IUserRepository;
import com.auth.usuario.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private IUserRepository userRepository;
    @GetMapping("/findAll")
    public List<User> findAllUsers(){
        return userRepository.findAll();
    }
}
