package com.example.keycloak.controller;


import com.example.keycloak.models.UserDTO;
import com.example.keycloak.serviceImpl.KeyCloakServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private KeyCloakServiceImpl keycloakService;

    @GetMapping("/users")
    public List<?> getUsers() {
        return keycloakService.getUsers();
    }
    @GetMapping("/token")
    public String getAccessToken(){
        return keycloakService.getAccessToken();
    }


}
