package com.example.demo.dao;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemDao {
    private final ArrayList<User> UserList;

    @Autowired
    public MemDao(ArrayList<User> userList) {
        UserList = userList;
    }

    public ArrayList<User> getAllUser(){
        return UserList;
    }

    public User getUser(String email){
        for(int i = 0; i < UserList.size(); i++){
            if(UserList.get(i).getEmail().equals(email)){
                return UserList.get(i);
            }
        }

        return null;
    }

    public void insertUser(User user){
        for(int i = 0; i < UserList.size(); i++){
            if(UserList.get(i).getEmail().equals(user.getEmail())){
                return;
            }
        }
        UserList.add(user);
    }

    public User removeUser(String email){
        for(int i = 0; i < UserList.size(); i++){
            if(UserList.get(i).getEmail().equals(email)){
                return UserList.remove(i);
            }
        }

        return null;
    }

    public User updateUser(User user){
        for(int i = 0; i < UserList.size(); i++){
            if(UserList.get(i).getEmail().equals(user.getEmail())){
                User tmp = UserList.remove(i);
                UserList.add(user);
                return tmp;
            }
        }
        return null;
    }
}
