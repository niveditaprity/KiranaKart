package com.ecommerce.KiranaKart.repository;

import com.ecommerce.KiranaKart.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository  extends JpaRepository<Category,Long> {
}
