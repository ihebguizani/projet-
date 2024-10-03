package com.example.keycloak.controller;


import com.example.keycloak.models.Product;
import com.example.keycloak.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/allProduct")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/product/{idProduct}")
    public Product getProductById(Long idProduct){
        return productService.getProductById(idProduct);
    }
}
