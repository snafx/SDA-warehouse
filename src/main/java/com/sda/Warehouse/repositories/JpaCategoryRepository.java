
package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface JpaCategoryRepository extends CrudRepository<Category, Long> {

}

