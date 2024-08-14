package com.ecommerce.KiranaKart.controller;


import com.ecommerce.KiranaKart.dto.CategoryDto;
import com.ecommerce.KiranaKart.entity.Category;
import com.ecommerce.KiranaKart.facade.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryFacade categoryFacade;

    @PostMapping("/add/new")
    ResponseEntity<String>addCategory(@RequestBody CategoryDto categoryDto) {
        try {
            categoryFacade.addCategory(categoryDto);
            return  ResponseEntity.status(HttpStatus.CREATED).body("New Category added successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while adding the product");

        }
    }

    @GetMapping("/list")
    ResponseEntity<List<Category>>retrieveCategoryList() {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(categoryFacade.retrieveCategoryList());
    }
}
