package ru.geekbrains.geekmarket.services;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.repositories.ProductRepository;
import ru.geekbrains.geekmarket.repositories.specifications.ProductSpecifications;

import java.util.Map;


@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public Page<Products> findAll(Map<String, String> params, int page, int size) {
        Specification<Products> spec = Specification.where(null);
        if(params.containsKey("title") && !params.get("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.get("title")));
        }
        if(params.containsKey("min") && !params.get("min").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Float.parseFloat(params.get("min"))));
        }
        if(params.containsKey("max") && !params.get("max").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(Float.parseFloat(params.get("max"))));
        }
        return productRepository.findAll(spec, PageRequest.of(page, size));
    }
}
