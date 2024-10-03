package com.example.keycloak.serviceImpl;

import com.example.keycloak.models.Application;
import com.example.keycloak.repositorie.ApplicationRepo;
import com.example.keycloak.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepo applicationRepo;

    @Override
    public Application create(Application application){
        return applicationRepo.save(application);
    }

    @Override
    public Application getApplicationById(Long applicationId){
        Optional<Application> optionalApplication = applicationRepo.findById(applicationId);
        if (optionalApplication.isPresent()){
            return optionalApplication.get();
        }
        return null;
    }

    @Override
    public List<Application> getALlApplication() {
        return applicationRepo.findAll();
    }

    @Override
    public Application updateApp(Application application, Long applicationId) {
        return applicationRepo.save(application);
    }

    @Override
    public List<Application> deleteApp(Long applicationId) {
        applicationRepo.deleteById(applicationId);
        return getALlApplication();
    }
}
