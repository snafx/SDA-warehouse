package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.processors.ProductsProcessor;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(path = "/products")
public class ProductsController {

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public ProductsController(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    // page with list of all products; path: "/products" or "/products?page=3"
    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        Integer currentPage = Integer.valueOf(page);

        ProductsProcessor productsProcessor = new ProductsProcessor(jpaProductRepository, jpaCategoryRepository, currentPage);

        List<Product> allProducts = productsProcessor.getProductList();
        Long totalElements = productsProcessor.getTotalProductsAmount();
        Integer totalPages = productsProcessor.getTotalPages();

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", ProductsProcessor.FIRST_PAGE);
        model.addAttribute("currentPage", currentPage);

        return "products";
    }

    // page with searched products; path: "/products?search-phrase=abc" or "/products?search-phrase=abc&page=3"
    @GetMapping(params = {"search-phrase"})
    public String searchedProducts(Model model,
                                   @RequestParam(value = "search-phrase") String searchPhrase,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        Integer currentPage = Integer.valueOf(page);

        ProductsProcessor productsProcessor = new ProductsProcessor(jpaProductRepository, jpaCategoryRepository, currentPage, searchPhrase);

        List<Product> allProducts = productsProcessor.getProductList();
        Long totalElements = productsProcessor.getTotalProductsAmount();
        Integer totalPages = productsProcessor.getTotalPages();

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", ProductsProcessor.FIRST_PAGE);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchPhrase", searchPhrase);

        return "products";
    }

    // page with sorted products; path: "/products?sort-by=kolumna&sort-type=asc/desc" or "/products?sort-by=kolumna&sort-type=asc/desc&page=3"
    @GetMapping(params = {"sort-by", "sort-type"})
    public String sortedProducts(Model model,
                                 @RequestParam(value = "sort-by", required = false, defaultValue = "") String sortColumn,
                                 @RequestParam(value = "sort-type", required = false, defaultValue = "") String sortType,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        Integer currentPage = Integer.valueOf(page);

        ProductsProcessor productsProcessor = new ProductsProcessor(jpaProductRepository, jpaCategoryRepository, currentPage, sortColumn, sortType);

        List<Product> allProducts = productsProcessor.getProductList();
        Long totalElements = productsProcessor.getTotalProductsAmount();
        Integer totalPages = productsProcessor.getTotalPages();

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", ProductsProcessor.FIRST_PAGE);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortType", sortType);

        return "products";
    }

    // page with searched and sorted products; path: "/products?search-phrase=abc&sort-by=kolumna&sort-type=asc/desc" or "/products?search-phrase=abc&sort-by=kolumna&sort-type=asc/desc&page=3"
    @GetMapping(params = {"search-phrase", "sort-by", "sort-type"})
    public String searchedAndSortedProducts(Model model,
                                            @RequestParam(value = "search-phrase", required = false, defaultValue = "") String searchPhrase,
                                            @RequestParam(value = "sort-by", required = false, defaultValue = "") String sortColumn,
                                            @RequestParam(value = "sort-type", required = false, defaultValue = "") String sortType,
                                            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        Integer currentPage = Integer.valueOf(page);

        ProductsProcessor productsProcessor = new ProductsProcessor(jpaProductRepository, jpaCategoryRepository, currentPage, searchPhrase, sortColumn, sortType);

        List<Product> allProducts = productsProcessor.getProductList();
        Long totalElements = productsProcessor.getTotalProductsAmount();
        Integer totalPages = productsProcessor.getTotalPages();

        model.addAttribute("allProducts", allProducts);
        model.addAttribute("totalElements", totalElements);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("firstPage", ProductsProcessor.FIRST_PAGE);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("searchPhrase", searchPhrase);
        model.addAttribute("sortColumn", sortColumn);
        model.addAttribute("sortType", sortType);

        return "products";
    }
}