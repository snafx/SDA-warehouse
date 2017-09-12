package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.OrderDetails;

import com.sda.Warehouse.models.UserOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface JpaOrderDetailsRepository extends CrudRepository<OrderDetails, Long> {

    List<OrderDetails> findByParentOrder(UserOrder userOrder);

}
