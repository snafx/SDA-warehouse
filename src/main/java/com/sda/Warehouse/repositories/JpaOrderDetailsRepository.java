package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.OrderDetails;
import org.springframework.data.repository.CrudRepository;

public interface JpaOrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

}
