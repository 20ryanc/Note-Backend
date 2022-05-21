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

    public User getUser(UUID id){
        return memdao.getUser(id);
    }

    public void addUser(User user){
        memdao.insertUser(user);
    }

    public User removeUser(UUID id){
        return memdao.removeUser(id);
    }

    public User updateUser(User user){
        return memdao.updateUser(user);
    }


}
