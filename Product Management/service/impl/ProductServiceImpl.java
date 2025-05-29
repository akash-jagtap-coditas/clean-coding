package com.example.productinventory.service.impl;

import com.example.productinventory.dto.ProductDto;
import com.example.productinventory.entity.Product;
import com.example.productinventory.mapper.ProductMapper;
import com.example.productinventory.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper mapper;

    public ProductServiceImpl(ProductRepository productRepository, ProductMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    @Override
    public ProductDto addProduct(ProductDto dto) {
        Product product = mapper.toEntity(dto);
        Product saved = productRepository.save(product);
        return mapper.toDto(saved);
    }

    @Override
    public Optional<ProductDto> getProductById(Long id) {
        return productRepository.findById(id).map(mapper::toDto);
    }

    @Override
    public Optional<ProductDto> updateProduct(Long id, ProductDto dto) {
        return productRepository.findById(id).map(existing -> {
            mapper.updateEntity(dto, existing);
            Product updated = productRepository.save(existing);
            return mapper.toDto(updated);
        });
    }

    @Override
    public boolean deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            return false;
        }
        productRepository.deleteById(id);
        return true;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }
}
