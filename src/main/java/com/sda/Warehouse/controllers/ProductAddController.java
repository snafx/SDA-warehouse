package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Category;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaBookAuthorRepository;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;


@Controller
public class ProductAddController extends WebMvcConfigurerAdapter {

    private JpaCategoryRepository categoryRepository;

    private JpaProductRepository productRepository;

    private JpaBookAuthorRepository bookAuthor;

    @Autowired
    public ProductAddController(JpaCategoryRepository categoryRepository, JpaProductRepository productRepository, JpaBookAuthorRepository bookAuthor) {
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.bookAuthor = bookAuthor;
    }

    @ModelAttribute("categories")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @ModelAttribute("bookAuthor")
    public Iterable<BookAuthor> getAllCategoris() {return bookAuthor.findAll();}

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addResult").setViewName("addResult");
    }

    @GetMapping("/addProduct")
    public String showForm(Product product, Model model) {
        model.addAttribute("categories");
        model.addAttribute("authorBook");
        return "addProduct";
    }

    @PostMapping("/check")
    public String checkProductInfo(@Valid Product product, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addProduct";
        }

        productRepository.save(product);
        return "redirect:/addResult";
    }
}
