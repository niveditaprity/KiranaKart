package com.ecommerce.KiranaKart.facade;

import com.ecommerce.KiranaKart.dto.CategoryDto;
import com.ecommerce.KiranaKart.entity.Category;
import com.ecommerce.KiranaKart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryFacade {
    @Autowired
    private CategoryService categoryService;
    public void addCategory(CategoryDto categoryDto) {
        categoryService.addCategory(categoryDto);

    }

    public List<Category> retrieveCategoryList() {
        return categoryService.retrieveCategoryList();

    }
}
