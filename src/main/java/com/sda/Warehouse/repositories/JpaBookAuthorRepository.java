package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.BookAuthor;
import com.sda.Warehouse.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;

public interface JpaBookAuthorRepository extends CrudRepository<BookAuthor, Long> {

    //interfejs, który dostarcza zbiór metoed związanych z create, read, update, delete dla klasy Category
    //nie trzeba tworzyć implementacjitego interfejsu - Spring samgo dostarczy

}
