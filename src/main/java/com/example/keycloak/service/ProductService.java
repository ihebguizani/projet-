package com.example.keycloak.service;

import com.example.keycloak.models.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Long idProduct);

}
