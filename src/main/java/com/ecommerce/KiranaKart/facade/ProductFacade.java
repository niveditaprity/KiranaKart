package com.ecommerce.KiranaKart.facade;

import com.ecommerce.KiranaKart.dto.ProductDto;
import com.ecommerce.KiranaKart.entity.Product;
import com.ecommerce.KiranaKart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ProductFacade {

    @Autowired
    private ProductService productService;
    public void addNewProduct(ProductDto productDto) {
        productService.addNewProduct(productDto);

    }

    public List<Product> productList() {
        return productService.productList();
    }
}
