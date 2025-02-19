package com.pruebakeycloak.dto;

import java.util.Set;

public record UserDTO(
        String username,
        String email,
        String firstName,
        String lastName,
        String password,
        Set<String> roles
) {
}
