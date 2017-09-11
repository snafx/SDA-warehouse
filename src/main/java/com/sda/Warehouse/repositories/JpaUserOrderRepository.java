package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.UserOrder;
import org.springframework.data.repository.CrudRepository;

public interface JpaUserOrderRepository extends CrudRepository<UserOrder, Long> {

}
