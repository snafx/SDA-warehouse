package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductController(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @GetMapping(value = "/product/{productId}")
    public ModelAndView singleProduct(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", jpaProductRepository.findOne(productId));
        return modelAndView;
    }

    @GetMapping(value = "/product")
    public ModelAndView singleProduct() {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", jpaProductRepository);
        return modelAndView;
    }

    @GetMapping(value = "/admin/product/{productId}")
    public ModelAndView adminProductView(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", jpaProductRepository.findOne(productId));
        return modelAndView;
    }

    @PostMapping(value = "/admin/product/{productId}")
    public String updateQuantity(@RequestParam(value = "quantity") Integer quantity, @PathVariable("productId") Long productId) {
        Product product = jpaProductRepository.findOne(productId);
        product.setQuantity(quantity);
        jpaProductRepository.save(product);
        return "redirect:/admin/product/{productId}/";
    }
}




