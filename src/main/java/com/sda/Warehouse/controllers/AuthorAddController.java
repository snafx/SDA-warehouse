package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaBookAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.validation.Valid;

@Controller
public class AuthorAddController extends WebMvcConfigurerAdapter {

    private JpaBookAuthorRepository authorRepository;

    @Autowired
    public AuthorAddController(JpaBookAuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/addAuthorResult").setViewName("addAuthorResult");
    }

    @GetMapping("/addAuthor")
    public String showForm(BookAuthor bookAuthor, Model model) {
        return "addAuthor";
    }

    @PostMapping("/checkAuthor")
    public String checkProductInfo(@Valid BookAuthor bookAuthor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addAuthor";
        }

        authorRepository.save(bookAuthor);
        return "redirect:/addProduct";
    }
}
