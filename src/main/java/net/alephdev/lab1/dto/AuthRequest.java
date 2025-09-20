package net.alephdev.lab1.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
