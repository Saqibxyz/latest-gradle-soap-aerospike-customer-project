package com.soap.model;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "customers")
public class Customer {
    @Id
    @NotBlank
    String id;
    @NotBlank
    @NotNull
    String name;
    int age;
    @NotNull
    @NotBlank
    String residence;
    double balance;

    public Customer() {
    }

    public Customer(String id, String name, int age, String residence, double balance) {
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
