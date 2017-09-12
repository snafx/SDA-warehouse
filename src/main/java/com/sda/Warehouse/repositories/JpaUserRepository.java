package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.User;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserRepository extends CrudRepository<User, Long> {

    User findOneByUsername(String username);
}
