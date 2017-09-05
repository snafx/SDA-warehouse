package com.sda.Warehouse.controllers;

import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @Autowired
    private JpaProductRepository jpaProductRepository;

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
}
