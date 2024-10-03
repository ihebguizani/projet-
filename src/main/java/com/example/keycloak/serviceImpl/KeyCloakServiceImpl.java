package com.example.keycloak.serviceImpl;


import jakarta.annotation.PostConstruct;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.ClientResource;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RealmsResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeyCloakServiceImpl {
    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;



    @Value("${keycloak-admin-username}")
    private String adminUsername;

    @Value("${keycloak-admin-password}")
    private String adminPassword;

    private Keycloak keycloak;

    @PostConstruct
    private Keycloak getInstance() {
        if (keycloak == null) {
            keycloak = KeycloakBuilder.builder()
                    .serverUrl(authServerUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .clientId(clientId)
                    .username(adminUsername)
                    .password(adminPassword)
                    .build();
        }
        return keycloak;
    }

    public List<?> getUsers() {
        RealmResource realmResource = getInstance().realm(realm);
        return realmResource.users().list();
    }
    public String getAccessToken() {
        String tokenUrl = authServerUrl + "realms/MySuperApplicationRealm/protocol/openid-connect/token";

        Map<String, String> body = new HashMap<>();
        body.put("grant_type", "password");
        body.put("client_id", clientId);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Map> response = restTemplate.postForEntity(tokenUrl, body, Map.class);
        return (String) response.getBody().get("access_token");
    }
    public List<ClientRepresentation> getClients() {
        RealmResource realmResource = getInstance().realm(realm);
        return realmResource.clients().findAll();
    }

    public List<ClientRepresentation> getClientByName(String clientName) {
        RealmResource realmResource = getInstance().realm(realm);
        return realmResource.clients().findByClientId(clientName);
    }
    public void createRole( RoleRepresentation roleRepresentation) {
        RealmResource realmResource = getInstance().realm(realm);
        realmResource.roles().create(roleRepresentation);
    }
    public void createRoleInClient(String clientUuid, RoleRepresentation roleRepresentation) {
        RealmResource realmResource = getInstance().realm(realm);
        ClientResource clientResource = realmResource.clients().get(clientUuid);
        clientResource.roles().create(roleRepresentation);
    }

    public List<RoleRepresentation> getRoles(){
        RealmResource realmResource = getInstance().realm(realm);
        return realmResource.roles().list();
    }

    public RoleRepresentation getRoleByName(String roleName){
        RealmResource realmResource = getInstance().realm(realm);
        return realmResource.rolesById().getRole(roleName);
    }

    public void deleteRoleById(String role_id){
         RealmResource realmResource = getInstance().realm(realm);
         realmResource.rolesById().deleteRole(role_id);
    }

    public void deleteUserById(String user_id){
        UsersResource usersResource = getInstance().realm(realm).users();
        usersResource.delete(user_id);
    }

    public UserRepresentation getUserById(String user_id){
        UsersResource usersResource = getInstance().realm(realm).users();
        UserRepresentation user = usersResource.get(user_id).toRepresentation();
        return user;
    }
    public void assignRoleToUser(String user_id,String roleName){
        RealmResource realmResource = keycloak.realm(realm);

        // Get the role representation by name
        RoleRepresentation role = realmResource.roles().get(roleName).toRepresentation();

        // Assign the role to the user
        realmResource.users()
                .get(user_id)
                .roles()
                .realmLevel()
                .add(Collections.singletonList(role));
    }






}
