package com.example.keycloak.controller;


import com.example.keycloak.serviceImpl.KeyCloakServiceImpl;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KeycloakController {
    @Autowired
    private KeyCloakServiceImpl keyCloakService;

    @GetMapping("/get-client-by-name")
    public List<ClientRepresentation> getClientByName(@RequestParam String clientName) {
        return keyCloakService.getClientByName(clientName);
    }

    @GetMapping("/get-clients")
    public List<ClientRepresentation> getClients() {
        return keyCloakService.getClients();
    }

    @PostMapping("/addRole/{clientUuid}")
    public void createRoleInClient( @PathVariable String clientUuid, @RequestBody RoleRepresentation roleRepresentation) {
        keyCloakService.createRoleInClient(clientUuid, roleRepresentation);
    }
    @PostMapping("/addRole")
    public void createRole(  @RequestBody RoleRepresentation roleRepresentation) {
        keyCloakService.createRole( roleRepresentation);
    }

    @GetMapping("/roles")
    public List<RoleRepresentation> getRoles() {
        return keyCloakService.getRoles();
    }

    @GetMapping("/roleByName/{clientUuid}")
    public RoleRepresentation getRoleByName(@RequestParam String roleName,@PathVariable String clientUuid){
        return keyCloakService.getRoleByName(roleName);
    }

    @DeleteMapping("/roles-by-id/{role_id}")
    public void deleteRoleById(@PathVariable String role_id){
         keyCloakService.deleteRoleById(role_id);
    }

    @DeleteMapping("/delete-users-by-id/{user_id}")
    public void deleteUserById(@PathVariable String user_id){
        keyCloakService.deleteUserById(user_id);
    }
    @GetMapping("/users-by-id/{user_id}")
    public UserRepresentation getUserById(@PathVariable String user_id){
        return keyCloakService.getUserById(user_id);
    }

    @PostMapping("/{userId}/roles/{roleName}")
    public void assignRoleToUser(@PathVariable String userId, @PathVariable String roleName) {
        keyCloakService.assignRoleToUser(userId, roleName);
    }
}
