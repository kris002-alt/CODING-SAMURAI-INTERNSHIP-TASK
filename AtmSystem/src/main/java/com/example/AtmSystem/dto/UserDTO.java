package com.example.AtmSystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class UserDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Size(min = 4, max = 4, message = "PIN must be 4 digits")
    private String pin;

    private List<AccountDTO> accounts = new ArrayList<>();

    public void setAccounts(List<AccountDTO> accounts) { this.accounts = accounts; }
}
