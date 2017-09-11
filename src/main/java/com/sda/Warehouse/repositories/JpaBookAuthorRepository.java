package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.BookAuthor;
import org.springframework.data.repository.CrudRepository;

public interface JpaBookAuthorRepository extends CrudRepository<BookAuthor, Long> {

}
