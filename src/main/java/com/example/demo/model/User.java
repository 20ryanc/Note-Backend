package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
import java.lang.ClassCastException;

public class User {

    private final UUID id;
    @NotBlank
    private String email;
    @NotBlank
    private String name;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("email") String email,
                @JsonProperty("name") String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        User tmp;
        try {
            tmp = (User) obj;
        }catch (ClassCastException e){
            return false;
        }
        if(this.id == tmp.getId() &&
                this.email.equals(tmp.getEmail()) &&
                this.name.equals(tmp.getName())){
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
