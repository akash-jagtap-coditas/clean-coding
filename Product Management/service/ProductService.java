package com.example.productinventory.service;

import com.example.productinventory.dto.ProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    ProductDto addProduct(ProductDto dto);

    Optional<ProductDto> getProductById(Long id);

    Optional<ProductDto> updateProduct(Long id, ProductDto dto);

    boolean deleteProduct(Long id);

    List<ProductDto> getAllProducts();
}
