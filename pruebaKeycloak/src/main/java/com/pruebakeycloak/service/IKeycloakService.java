package com.pruebakeycloak.service;

import com.pruebakeycloak.dto.UserDTO;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public interface IKeycloakService {
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> findUsersByUsername(String name);
    String createUser(UserDTO request);
    void deleteUserByID(String userId);
    void updateUser(String userId,UserDTO request);



}
