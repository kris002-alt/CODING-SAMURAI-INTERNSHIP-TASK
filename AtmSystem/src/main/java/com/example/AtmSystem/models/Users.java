package com.example.AtmSystem.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    private String pin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Account> accounts = new ArrayList<>();

    // Constructors
    public Users() {}

    public Users(String username, String firstName, String lastName, String pin) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pin = pin;
    }

    // Helper method to add account
    public void addAccount(Account account) {
        accounts.add(account);
        account.setUser(this);
    }

    // Helper method to remove account
    public void removeAccount(Account account) {
        accounts.remove(account);
        account.setUser(null);
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getPin() { return pin; }
    public void setPin(String pin) { this.pin = pin; }

    public List<Account> getAccounts() { return accounts; }
    public void setAccounts(List<Account> accounts) { this.accounts = accounts; }
}