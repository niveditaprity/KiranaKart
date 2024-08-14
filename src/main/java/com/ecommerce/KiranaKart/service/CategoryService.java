package com.ecommerce.KiranaKart.service;

import com.ecommerce.KiranaKart.dto.CategoryDto;
import com.ecommerce.KiranaKart.entity.Category;
import com.ecommerce.KiranaKart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.emitter.ScalarAnalysis;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    public void addCategory(CategoryDto categoryDto) {
        Category category  = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        categoryRepository.save(category);
    }

    public List<Category> retrieveCategoryList() {
        return categoryRepository.findAll();
    }
}
