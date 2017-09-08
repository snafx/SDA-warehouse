
package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface JpaProductRepository extends PagingAndSortingRepository<Product, Long> {

    //interfejs, który dostarcza zbiór metoed związanych z create, read, update, delete dla klasy Category
    //nie trzeba tworzyć implementacjitego interfejsu - Spring samgo dostarczy

    //    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

    Page<Product> findByNameIgnoreCaseContainingOrDescriptionIgnoreCaseContaining(String name, String description, Pageable pageable);

}

