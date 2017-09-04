package com.sda.Warehouse.controller;

import com.sda.Warehouse.models.Category;
import com.sda.Warehouse.models.ProductAddForm;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
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
public class ProductAddController extends WebMvcConfigurerAdapter{

    @Autowired
    JpaCategoryRepository categoryRepository;

    @ModelAttribute("categories")
    public Iterable<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    @PostMapping("/")
    public String showForm(ProductAddForm productAddForm, Model model) {
        model.addAttribute("categories");
        return "form";
    }

    @PostMapping("/")
    public String checkProductInfo(@Valid ProductAddForm productAddForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        return "redirect:/results";
    }
}
