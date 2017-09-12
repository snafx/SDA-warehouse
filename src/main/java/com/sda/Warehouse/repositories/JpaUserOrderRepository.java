package com.sda.Warehouse.repositories;

import com.sda.Warehouse.models.User;
import com.sda.Warehouse.models.UserOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface JpaUserOrderRepository extends CrudRepository<UserOrder, Long> {

    UserOrder findOneByOwnerAndIsApprovedIsFalse(User user);

    List<UserOrder> findByOwner(User user);

}
