package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.UUID;
import java.lang.ClassCastException;

@Entity
@Table(name = "users")
public class User {
    @Id
    private String email;
    @NotBlank
    private String name;

    public User(){

    }
    public User(@JsonProperty("email") String email,
                @JsonProperty("name") String name) {
        this.email = email;
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
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
        if(this.email.equals(tmp.getEmail()) &&
                this.name.equals(tmp.getName())){
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "User{" +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
