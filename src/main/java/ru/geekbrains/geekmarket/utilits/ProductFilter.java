package ru.geekbrains.geekmarket.utilits;

import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.repositories.specifications.ProductSpecifications;

import java.util.Map;

@Getter
public class ProductFilter {
    private Specification<Products> spec;
    private String filterDefinition;

    public ProductFilter(Map<String, String> params) {
        StringBuilder filterDefinitionBuilder = new StringBuilder();
        spec = Specification.where(null);
        String filterTitle = params.get("title");

        if(filterTitle != null && !filterTitle .isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(filterTitle));
            filterDefinitionBuilder.append("&title=").append(filterTitle);
        }

        if(params.containsKey("min") && !params.get("min").isBlank()) {
            Float min = Float.parseFloat(params.get("min"));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(min));
            filterDefinitionBuilder.append("&min=").append(min);
        }

        if(params.containsKey("max") && !params.get("max").isBlank()) {
            Float max = Float.parseFloat(params.get("max"));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(max));
            filterDefinitionBuilder.append("&max=").append(max);
        }
        filterDefinition = filterDefinitionBuilder.toString();
    }

    public Specification<Products> getSpec() {
        return spec;
    }

    public String getFilterDefinition() {
        return filterDefinition;
    }
}
