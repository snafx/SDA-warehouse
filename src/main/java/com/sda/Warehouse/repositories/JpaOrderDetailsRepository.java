package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.OrderDetails;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mytek on 2017-09-09.
 */
public interface JpaOrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

}
