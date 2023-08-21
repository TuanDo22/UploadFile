package com.example.javaspringfram;

import lombok.Data;

import java.io.Serializable;

public class User implements Serializable{
    public String name;
    public String address ;
    public int age ;

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

