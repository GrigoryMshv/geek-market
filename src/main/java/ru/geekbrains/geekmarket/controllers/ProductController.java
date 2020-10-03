package ru.geekbrains.geekmarket.controllers;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.geekmarket.entities.Products;
import ru.geekbrains.geekmarket.services.ProductService;

import java.util.Map;


@Controller
@RequestMapping("/products")
@AllArgsConstructor
public class ProductController {
   private static final int PAGE_SIZE = 5;
   private ProductService productService;

   @GetMapping
   public String showAllProducts(Model model, @RequestParam(defaultValue = "1", name = "p") Integer page,
                                 Map<String, String> params
                                 ) {
      if (page < 1) {
         page = 1;
      }
      Page<Products> products = productService.findAll(params, page - 1, PAGE_SIZE);
      model.addAttribute("products", products);
      return "products";
   }
}
