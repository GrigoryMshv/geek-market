package ru.geekbrains.geekmarket.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.geekmarket.entities.Products;

public class ProductSpecifications {
    public static Specification<Products> priceGreaterOrEqualsThan(Float min) {
        return (Specification<Products>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), min);
    }

    public static Specification<Products> priceLesserOrEqualsThan(Float max) {
        return (Specification<Products>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), max);
    }

    public static Specification<Products> titleLike (String title) {
        return (Specification<Products>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", title));
    }
}
