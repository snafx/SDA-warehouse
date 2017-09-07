package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.processors.ProductsProcessor;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class ProductsController {

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;

    @Autowired
    public ProductsController(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
    }

    // strona w wszystkimi produktami; sciezka: "/products" lub "/products?page=3"

    @GetMapping
    public String allProducts(Model model,
                              @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        System.out.println("JESTEM W OGOLNYM KONTROLERZE");

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

    // strona w wyszukanymi produktami; sciezka: "/products?search-phrase=abc" lub "/products?search-phrase=abc&page=3"

    @GetMapping(params = {"search-phrase"})
    public String searchedProducts(Model model,
                                   @RequestParam(value = "search-phrase") String searchPhrase,
                                   @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        System.out.println("JESTEM W KONTROLERZE OD WYSZUKIWANIA");

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

    // strona posortowanymi produktami; sciezka: "/products?sort-by=kolumna&sort-type=asc/desc" lub "/products?sort-by=kolumna&sort-type=asc/desc&page=3"

    @GetMapping(params = {"sort-by", "sort-type"})
    public String sortedProducts(Model model,
                                 @RequestParam(value = "sort-by", required = false, defaultValue = "") String sortColumn,
                                 @RequestParam(value = "sort-type", required = false, defaultValue = "") String sortType,
                                 @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        System.out.println("JESTEM W KONTROLERZE OD SORTOWANIA");

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

    // strona wyszukanymi i posortowanymi produktami; sciezka: "/products?search-phrase=abc&sort-by=kolumna&sort-type=asc/desc" lub "/products?search-phrase=abc&sort-by=kolumna&sort-type=asc/desc&page=3"

    @GetMapping(params = {"search-phrase", "sort-by", "sort-type"})
    public String searchedAndSortedProducts(Model model,
                                            @RequestParam(value = "search-phrase", required = false, defaultValue = "") String searchPhrase,
                                            @RequestParam(value = "sort-by", required = false, defaultValue = "") String sortColumn,
                                            @RequestParam(value = "sort-type", required = false, defaultValue = "") String sortType,
                                            @RequestParam(value = "page", required = false, defaultValue = "1") String page) {

        System.out.println("JESTEM W KONTROLERZE OD WYSZUKIWANIA I SORTOWANIA");

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
