package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class UserAPI {
    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ArrayList<User> getAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("{Email}")
    public User getUser(@PathVariable("Email") String email){
        return userService.getUser(email);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("{Email}")
    public void deleteUser(@PathVariable("Email") String email){
        userService.removeUser(email);
    }

}
