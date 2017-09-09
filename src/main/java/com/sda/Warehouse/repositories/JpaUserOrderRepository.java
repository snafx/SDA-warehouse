package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.UserOrder;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by mytek on 2017-09-09.
 */
public interface JpaUserOrderRepository extends CrudRepository<UserOrder, Long> {

}
