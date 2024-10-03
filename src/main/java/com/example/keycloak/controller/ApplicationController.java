package com.example.keycloak.controller;

import com.example.keycloak.models.Application;
import com.example.keycloak.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/application")
@RestController
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;

    @GetMapping("/getAll")
    public List<Application> getAllApplication(){
        return applicationService.getALlApplication();
    }
    @PostMapping("/create")
    public Application create(@RequestBody Application application){
        return applicationService.create(application);
    }

    @DeleteMapping("/delete/{applicationId}")
    public List<Application> deleteApp(@PathVariable Long applicationId){
        return applicationService.deleteApp(applicationId);
    }

    @PutMapping("/update/{applicationId}")
    public Application updateApp(@RequestBody Application application, @PathVariable Long applicationId){
        return applicationService.updateApp(application, applicationId);
    }
    @GetMapping("/getById/{applicationId}")
    public Application getApplicationById(@PathVariable Long applicationId){
        return applicationService.getApplicationById(applicationId);
    }
}
