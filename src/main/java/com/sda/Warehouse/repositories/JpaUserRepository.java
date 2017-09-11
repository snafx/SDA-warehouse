package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaUserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);

}

