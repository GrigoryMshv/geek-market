package ru.geekbrains.geekmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.repositories.ProductRepository;

import java.util.Optional;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Products> findAll(Specification<Products> spec, int page, int size) {
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }

    public Optional<Products> findById(Long id) {
        return productRepository.findById(id);
    }

    public Products saveOrUpdate(Products products) {
        return productRepository.save(products);
    }
}
