package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    // strona w wszystkimi produktami; sciezka: "/products"

    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "page", required=false, defaultValue = "1") String page,
                              @RequestParam(value = "search-phrase", required=false, defaultValue = "") String searchPhrase,
                              @RequestParam(value = "sort-by", required=false, defaultValue = "") String sortColumn,
                              @RequestParam(value = "sort-type", required=false, defaultValue = "") String sortType) {

//        Iterable<Product> allProducts = jpaProductRepository.findAll();

        Integer productsPerPage = 5;
        Integer firstPage = 1;
        Integer currentPage = Integer.valueOf(page);

        PageRequest myPageabble = new PageRequest(currentPage - 1, productsPerPage);

        Page<Product> pageOfProducts = null;

        if (searchPhrase != "") {
            pageOfProducts = jpaProductRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchPhrase.toUpperCase(), searchPhrase.toUpperCase(), myPageabble);
        } else {
            pageOfProducts = jpaProductRepository.findAll(myPageabble);
        }

        Long totalElements = pageOfProducts.getTotalElements();
        Integer totalPages = pageOfProducts.getTotalPages();
        List<Product> allProducts = pageOfProducts.getContent();

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", firstPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchPhrase", searchPhrase);

        return "products";
    }


    @PostMapping(params = {"search-phrase"})
    public String searchAllProducts(Model model, @RequestParam(value = "search-phrase") String searchPhrase) {

//        List<Product> allProducts = jpaProductRepository.findByNameContainingOrDescriptionContaining(searchPhrase, searchPhrase);

//        model.addAttribute("allProducts", allProducts);
        model.addAttribute("searchPhrase", searchPhrase);

        return "products";
    }
}
