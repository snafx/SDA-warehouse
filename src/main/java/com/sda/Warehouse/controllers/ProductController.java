package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mytek on 2017-09-02.
 */

@Controller
@RequestMapping(path = "/products")
public class ProductController {

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public ProductController(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    // strona w wszystkimi produktami

    @GetMapping
    public String allProducts(Model model) {

        Iterable<Product> allProducts = jpaProductRepository.findAll();

        model.addAttribute("allProducts", allProducts);

        return "products";
    }
}
