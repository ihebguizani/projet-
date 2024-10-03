package com.example.keycloak.service;

import com.example.keycloak.models.Application;

import java.util.List;

public interface ApplicationService {
    List<Application> getALlApplication();

    Application create(Application application);
    Application getApplicationById(Long applicationId);
    List<Application> deleteApp(Long applicationId);
    Application updateApp(Application application, Long applicationId);


}
