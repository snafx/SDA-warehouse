package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.UserOrder;
import com.sda.Warehouse.processors.ProductsProcessor;
import com.sda.Warehouse.repositories.JpaCategoryRepository;
import com.sda.Warehouse.repositories.JpaOrderDetailsRepository;
import com.sda.Warehouse.repositories.JpaProductRepository;
import com.sda.Warehouse.repositories.JpaUserOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by RENT on 2017-09-09.
 */

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;
    private JpaUserOrderRepository jpaUserOrderRepository;
    private JpaOrderDetailsRepository jpaOrderDetailsRepository;

    @Autowired
    public OrdersController(JpaProductRepository jpaProductRepository,
                            JpaCategoryRepository jpaCategoryRepository,
                            JpaUserOrderRepository jpaUserOrderRepository,
                            JpaOrderDetailsRepository jpaOrderDetailsRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaUserOrderRepository = jpaUserOrderRepository;
        this.jpaOrderDetailsRepository = jpaOrderDetailsRepository;
    }

    @GetMapping(value = "/mylist")
    public String allUserOrders(Model model) {

        Iterable<UserOrder> allOrders = jpaUserOrderRepository.findAll();
        model.addAttribute("allOrders", allOrders);

        return "userOrders";
    }
}
