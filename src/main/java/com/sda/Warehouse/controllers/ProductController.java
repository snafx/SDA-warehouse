package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaProductRepository;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    @GetMapping(value = "/product/{productId}/changeAmount")
    public ModelAndView change(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("stockAmountChange");
        Product product = jpaProductRepository.findOne(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }


    @PostMapping(value = "/product/{productId}/changeAmount")
    public String changeAmount(@RequestParam(value = "quantity") Integer quantity, @PathVariable("productId") Long productId) {
        Product product = jpaProductRepository.findOne(productId);
        product.setQuantity(quantity);
        jpaProductRepository.save(product);
        return "redirect:/product/{productId}/";
    }

}
