package com.example.demo.service;

import com.example.demo.dao.MemDao;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;


@Service
public class UserService {
    private final MemDao memdao;

    @Autowired
    public UserService(MemDao memdao) {
        this.memdao = memdao;
    }

    public ArrayList<User> getAllUser(){
        return memdao.getAllUser();
    }

    public User getUser(String email){
        return memdao.getUser(email);
    }

    public void addUser(User user){
        memdao.insertUser(user);
    }

    public User removeUser(String email){
        return memdao.removeUser(email);
    }

    public User updateUser(User user){
        return memdao.updateUser(user);
    }


}
