package com.pruebakeycloak.controller;

import com.pruebakeycloak.dto.UserDTO;
import com.pruebakeycloak.service.IKeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keycloak/user")
@PreAuthorize("hasRole('admin_client_role')")
public class KeycloakController {
    @Autowired
    private IKeycloakService keycloakService;

    //-1 Crear usuario
    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody UserDTO request){
        String response = keycloakService.createUser(request);
        return ResponseEntity.ok(response);

    }
    //-2 Buscar usuario
    @GetMapping("/find/{userId}")
    public ResponseEntity<?> searchUserByUsername(@PathVariable String userId){
        return ResponseEntity.ok(keycloakService.findUsersByUsername(userId));
    }
    //-3 Buscar todos
    @GetMapping("/findAll")
    public ResponseEntity<?> findAllUsers(){
        return ResponseEntity.ok(keycloakService.findAllUsers());
    }
    //-4 Eliminar usuario
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId){
        keycloakService.deleteUserByID(userId);
        return ResponseEntity.noContent().build();
    }

}
