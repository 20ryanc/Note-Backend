package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public User getUser(String email){
        return userRepository.findById(email).get();
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUser(String email){
        userRepository.deleteById(email);
    }

    public void updateUser(User user){
        removeUser(user.getEmail());
        addUser(user);
    }


}
