package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.repositories.JpaBookAuthorRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping
public class BookAuthorController {

    private JpaBookAuthorRepository jpabookAuthorRepository;

    private JpaProductRepository jpaProductRepository;

    @Autowired
    public BookAuthorController(JpaBookAuthorRepository jpaBookAuthorRepository, JpaProductRepository jpaProductRepository){
        this.jpabookAuthorRepository = jpaBookAuthorRepository;
        this.jpaProductRepository = jpaProductRepository;
    }

    @GetMapping(path = "bookAuthors")
    public ModelAndView bookAuthors(){
        ModelAndView modelAndView = new ModelAndView("bookAuthor");
        modelAndView.addObject("allBooksAuthor", jpabookAuthorRepository.findAll());
        return modelAndView;
    }


    @GetMapping(path = "bookAuthor/{id}")
    public ModelAndView oneBookAuthor(@PathVariable("id") Long id){
        BookAuthor bookAuthor = jpabookAuthorRepository.findOne(id);
        ModelAndView modelAndView = new ModelAndView("bookAuthor");
        modelAndView.addObject("bookAuthor", bookAuthor);
        modelAndView.addObject("allBooksOneAuthor", jpaProductRepository.findByBookAuthor(bookAuthor));
        return modelAndView;
    }
}
