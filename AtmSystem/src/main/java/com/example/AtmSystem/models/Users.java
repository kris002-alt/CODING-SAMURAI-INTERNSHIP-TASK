package com.example.AtmSystem.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    private String firstName;
    private String lastName;

    @Column(nullable = false)
    private String pin; // Encrypted

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    // Constructors, getters, setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPin() {
        return pin;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public Users(Long id, String username, String firstName, String lastName, String pin, List<Account> accounts) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
        this.accounts = accounts;
    }
    public Users() {  }

}
