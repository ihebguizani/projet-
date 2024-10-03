package com.example.keycloak.serviceImpl;

import com.example.keycloak.models.Product;
import com.example.keycloak.repositorie.ProductRepo;
import com.example.keycloak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepo productRepo;

    @Override
    public List<Product> getAllProducts(){
        return productRepo.findAll();
    }

    @Override
    public Product getProductById(Long idProduct){
        return productRepo.findById(idProduct).get();
    }
}
