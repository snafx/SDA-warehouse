package com.sda.Warehouse.controllers;


import com.sda.Warehouse.models.Product;

import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(path = "/products")
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
    public ModelAndView loadProductParametersToEdit(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("editProduct");
        Product product = jpaProductRepository.findOne(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @GetMapping(value = "/product/{productId}/changeAmount")
    public ModelAndView change(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("stockAmountChange");
        Product product = jpaProductRepository.findOne(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }


    @PostMapping(path = "/product/{productId}/edit")
    public String editProduct(@ModelAttribute Product product) {
        jpaProductRepository.save(product);
        return "redirect:/products/product/{productId}/";
    }

    @PostMapping(value = "/product/{productId}/changeAmount")
    public String changeAmount(@RequestParam(value = "quantity") Integer quantity, @PathVariable("productId") Long productId) {
        Product product = jpaProductRepository.findOne(productId);
        product.setQuantity(quantity);
        jpaProductRepository.save(product);
        return "redirect:/products/product/{productId}/";
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
        return "redirect:/products/admin/product/{productId}/";
    }

    @GetMapping(path = "/product/{productId}/delete")
    public ModelAndView loadProductParametersToDelete(@PathVariable("productId") Long productId) {
        ModelAndView modelAndView = new ModelAndView("deleteProduct");
        Product product = jpaProductRepository.findOne(productId);
        modelAndView.addObject("product", product);
        return modelAndView;
    }

    @PostMapping(path = "/product/{productId}/delete")
    public String deleteProduct(@ModelAttribute Product product) {
        jpaProductRepository.delete(product);
        return "redirect:/products/";
    }

    @PostMapping(path = "/product/{productId}/delete")
    public String deleteProduct(@PathVariable("productId") Long productId) {
        jpaProductRepository.delete(productId);
        return "redirect:/products/";
    }

}




