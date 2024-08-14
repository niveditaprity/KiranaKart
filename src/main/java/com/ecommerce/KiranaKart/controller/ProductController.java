package com.ecommerce.KiranaKart.controller;


import com.ecommerce.KiranaKart.dto.ProductDto;
import com.ecommerce.KiranaKart.entity.Product;
import com.ecommerce.KiranaKart.facade.ProductFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductFacade productFacade;

    @PostMapping("/add")
    public ResponseEntity<String> addNewProduct(@Valid @RequestBody ProductDto productDto) {
        try {
            productFacade.addNewProduct(productDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("New product is added successfully");
        } catch (Exception e) {
            // Log the exception details;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the product");
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<Product>>productList() {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(productFacade.productList());
    }
}


