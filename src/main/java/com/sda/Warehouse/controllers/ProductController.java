package com.sda.Warehouse.controllers;


import com.sda.Warehouse.models.Product;

import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    private JpaProductRepository jpaProductRepository;

    @Autowired
    public ProductController(JpaProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }


    @GetMapping(path = "/product/{productId}")
    public ModelAndView singleProduct(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", jpaProductRepository.findOne(productId));
        return modelAndView;
    }

    @GetMapping(path = "/product")
    public ModelAndView singleProduct() {
        ModelAndView modelAndView = new ModelAndView("product");
        modelAndView.addObject("product", jpaProductRepository);
        return modelAndView;
    }

    @GetMapping(path = "/product/{productId}/edit")
    public ModelAndView loadProductParameters(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("editproduct");
        Product product = jpaProductRepository.findOne(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(path = "/product/{productId}/edit")
    public String editProduct(@ModelAttribute Product product, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return "form";
        }
        jpaProductRepository.save(product);
        return "redirect:/product/{productId}/";
    }

    @GetMapping(value = "/edit")
    public String editStart() {
        return "edit-product";
    }
}
