package ru.geekbrains.geekmarket.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.geekmarket.entities.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Long> {

    Page<Products> getProductByPriceGreaterThanEqual(Float min, Pageable var);
    Page<Products> getProductByPriceLessThanEqual(Float max, Pageable var);
    Page<Products> getProductByPriceGreaterThanEqualAndPriceLessThanEqual(Float min, Float max, Pageable var);
}
