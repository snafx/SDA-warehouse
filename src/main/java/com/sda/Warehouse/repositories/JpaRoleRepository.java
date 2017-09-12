package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface JpaRoleRepository extends CrudRepository<Role, Long> {


}
