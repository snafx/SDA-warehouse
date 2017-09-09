package com.sda.Warehouse.processors;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mytek on 2017-09-06.
 */
public class ProductsProcessor {

    public static final Integer PRODUCTS_PER_PAGE = 5;
    public static final Integer FIRST_PAGE = 1;

    private List<Product> productList;

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;

    private Long totalProductsAmount;
    private Integer totalPages;

    public ProductsProcessor(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository, Integer currentPage) {

        this(jpaProductRepository, jpaCategoryRepository, currentPage, "", "", "");
    }

    public ProductsProcessor(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository, Integer currentPage, String searchPhrase) {

        this(jpaProductRepository, jpaCategoryRepository, currentPage, searchPhrase, "", "");
    }

    public ProductsProcessor(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository, Integer currentPage, String sortColumn, String sortType) {

        this(jpaProductRepository, jpaCategoryRepository, currentPage, "", sortColumn, sortType);
    }

    public ProductsProcessor(JpaProductRepository jpaProductRepository, JpaCategoryRepository jpaCategoryRepository, Integer currentPage, String searchPhrase, String sortColumn, String sortType) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;

        PageRequest myPageabble = null; //do poprawki
        if (!sortColumn.equals("") && !sortColumn.equals(null) && !sortType.equals("") && !sortType.equals(null)) {

            Sort.Direction sortDirection = Sort.Direction.ASC;
            if (sortType.equals("desc")) {
                sortDirection = Sort.Direction.DESC;
            }

            String sortColumnName = "name";
            if (sortColumn.equals("category")) {
                sortColumnName = "category.name";
            }

            Sort.Order mySortOrder = new Sort.Order(sortDirection, sortColumnName);
            Sort mySort = new Sort(mySortOrder);

            myPageabble = new PageRequest(currentPage - 1, PRODUCTS_PER_PAGE, mySort);
        } else {
            myPageabble = new PageRequest(currentPage - 1, PRODUCTS_PER_PAGE);
        }

        Page<Product> pageOfProducts = null; //do poprawki
        if (!searchPhrase.equals("") && !searchPhrase.equals(null)) {
            pageOfProducts = jpaProductRepository.findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(searchPhrase.toUpperCase(), searchPhrase.toUpperCase(), myPageabble);
        } else {
            pageOfProducts = jpaProductRepository.findAll(myPageabble);
        }

        this.productList = pageOfProducts.getContent();
        this.totalProductsAmount = pageOfProducts.getTotalElements();
        this.totalPages = pageOfProducts.getTotalPages();
    }

    public List<Product> getProductList() {
        return productList;
    }

    public Long getTotalProductsAmount() {
        return totalProductsAmount;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}