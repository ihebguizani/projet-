package com.example.keycloak.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRepresentation {
    private String id;
    private String clientId;
    private String name;
    private String description;
    private Boolean enabled;
    private String clientAuthenticatorType;
    private List<String> redirectUris;
    private List<String> webOrigins;
    private String protocol;
    private Map<String, String> attributes;
}
