package ru.geekbrains.geekmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.repositories.ProductRepository;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Products> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Products> getMaxPrice(int page, int size, Float max) {
        return productRepository.getProductByPriceGreaterThanEqual(max, PageRequest.of(page, size));
    }

    public Page<Products> getMinPrice(int page, int size, Float min) {
        return productRepository.getProductByPriceLessThanEqual(min, PageRequest.of(page, size));
    }

    public Page<Products> getMinAndMaxPrice(int page, int size, Float min, Float max) {
        return productRepository.getProductByPriceGreaterThanEqualAndPriceLessThanEqual(min, max, PageRequest.of(page, size));
    }
}
