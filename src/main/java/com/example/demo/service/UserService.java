package com.example.demo.service;

import com.example.demo.dao.UserRepository;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public List<User> getAllUser(){
        return userRepository.findAll();
    }

    public Optional<User> getUser(String email){
        return userRepository.findById(email);
    }

    public void addUser(User user){
        userRepository.save(user);
    }

    public void removeUser(String email){
        userRepository.deleteById(email);
    }

    public void updateUser(User user){
        removeUser(user.getUsername());
        addUser(user);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> usr = userRepository.findById(username);
        if(usr.isEmpty()){
            throw new UsernameNotFoundException("User not present");
        }
        return usr.get();
    }
}
