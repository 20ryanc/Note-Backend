package com.example.demo.api;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserAPI {
    private final UserService userService;

    @Autowired
    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @GetMapping("/{email}")
    public User getUser(@PathVariable("email") String email){
        System.out.println(email);
        return userService.getUser(email);
    }

    @PostMapping
    public void addUser(@RequestBody User user){
        System.out.println(user);
        userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@RequestBody User user){
        userService.updateUser(user);
    }

    @DeleteMapping("/{email}")
    public void deleteUser(@PathVariable("email") String email){
        System.out.println(email);
        userService.removeUser(email);
    }

}
