
package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JpaProductRepository extends PagingAndSortingRepository<Product, Long> {

    Page<Product> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name, String description, Pageable pageable);

    List<Product> findByBookAuthor(BookAuthor bookAuthor);
}

