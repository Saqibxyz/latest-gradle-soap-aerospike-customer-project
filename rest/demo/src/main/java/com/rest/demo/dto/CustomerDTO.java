package com.rest.demo.dto;

import com.sun.istack.NotNull;
import jakarta.validation.constraints.NotBlank;

public class CustomerDTO {
    @NotNull
    @NotBlank(message = "Id is mandatory")
    String id;
    @NotNull
    @NotBlank(message = "Name is mandatory")
    String name;
    int age;
    @NotNull
    @NotBlank(message = "Name is mandatory")
    String residence;
    double balance;

    public CustomerDTO(String id, String name, int age, String residence, double balance) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.residence = residence;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

}
