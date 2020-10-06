package ru.geekbrains.geekmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.exceptions.ResourceNotFoundException;
import ru.geekbrains.geekmarket.services.ProductService;
import ru.geekbrains.geekmarket.utilits.ProductFilter;

import java.util.Map;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
   private static final int PAGE_SIZE = 5;
   private ProductService productService;

   @GetMapping
   public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page,
                                              @RequestParam Map<String, String> params) {
      if (page < 1) {
         page = 1;
      }
      ProductFilter productFilter = new ProductFilter(params);
      Page<Products> products = productService.findAll(productFilter.getSpec(), page - 1, PAGE_SIZE);
      model.addAttribute("products", products);
      model.addAttribute("filterDefinition", productFilter.getFilterDefinition());
      return "products";
   }

   @GetMapping("/edit/{id}")
   public String showEditForm(@PathVariable Long id, Model model) {
      Products p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product with id: " + id + " doesn't exists (for edit)"));
      model.addAttribute("products", p);
      return "products";

   }
}
