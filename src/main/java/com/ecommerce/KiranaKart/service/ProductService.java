package com.ecommerce.KiranaKart.service;

import com.ecommerce.KiranaKart.dto.ProductDto;
import com.ecommerce.KiranaKart.entity.Product;
import com.ecommerce.KiranaKart.exception.ProductAlreadyExistsException;
import com.ecommerce.KiranaKart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ProductService {


    @Autowired
    private ProductRepository productRepository;
    public void addNewProduct(ProductDto productDto) {
        // Validate and map the DTO to the entity
        Product product = mapToProduct(productDto);

        // Additional business logic (e.g., check if the product already exists)
        if (productRepository.existsBySku(productDto.getSku())) {
            throw new ProductAlreadyExistsException("Product with SKU " + productDto.getSku() + " already exists.");
        }
        product.setDateAdded(LocalDate.now());
        productRepository.save(product);
    }

    private Product mapToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory_id(productDto.getCategory_id());
        product.setBrand(productDto.getBrand());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setSku(productDto.getSku());
        product.setQuantity(productDto.getQuantity());
        product.setStatus(productDto.getStatus());
        product.setImageUrl(productDto.getImageUrl());
        return product;
    }

    public List<Product> productList() {
        return productRepository.findAll();
    }
}
