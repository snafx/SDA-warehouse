package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface JpaCategoryRepository extends CrudRepository<Category, Long> {

    //interfejs, który dostarcza zbiór metoed związanych z create, read, update, delete dla klasy Category
    //nie trzeba tworzyć implementacjitego interfejsu - Spring samgo dostarczy

}
