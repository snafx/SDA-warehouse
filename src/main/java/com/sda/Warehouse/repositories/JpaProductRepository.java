package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by RENT on 2017-09-02.
 */
public interface JpaProductRepository extends CrudRepository<Product, Integer> {

    //interfejs, który dostarcza zbiór metoed związanych z create, read, update, delete dla klasy Category
    //nie trzeba tworzyć implementacjitego interfejsu - Spring samgo dostarczy

    List<Product> findByNameContainingOrDescriptionContaining(String name, String description);

}
