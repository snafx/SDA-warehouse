package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface JpaProductRepository extends CrudRepository<Product, Long> {

    //interfejs, który dostarcza zbiór metoed związanych z create, read, update, delete dla klasy Category
    //nie trzeba tworzyć implementacjitego interfejsu - Spring samgo dostarczy

}
