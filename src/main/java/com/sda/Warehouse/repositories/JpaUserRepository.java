package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JpaUserRepository extends CrudRepository<User, Long> {
    List<User> findByLastName(String lastName);
    User findOneByUsername(String username);
}
