package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.User;
import com.sda.Warehouse.models.UserOrder;
import com.sda.Warehouse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/orders")
public class OrdersController {

    private JpaProductRepository jpaProductRepository;
    private JpaCategoryRepository jpaCategoryRepository;
    private JpaUserOrderRepository jpaUserOrderRepository;
    private JpaOrderDetailsRepository jpaOrderDetailsRepository;
    private JpaUserRepository jpaUserRepository;

    @Autowired
    public OrdersController(JpaProductRepository jpaProductRepository,
                            JpaCategoryRepository jpaCategoryRepository,
                            JpaUserOrderRepository jpaUserOrderRepository,
                            JpaOrderDetailsRepository jpaOrderDetailsRepository,
                            JpaUserRepository jpaUserRepository) {
        this.jpaProductRepository = jpaProductRepository;
        this.jpaCategoryRepository = jpaCategoryRepository;
        this.jpaUserOrderRepository = jpaUserOrderRepository;
        this.jpaOrderDetailsRepository = jpaOrderDetailsRepository;
        this.jpaUserRepository = jpaUserRepository;
    }

    @GetMapping(value = "/mylist")
    public String allUserOrders(Model model) {

        Iterable<UserOrder> allOrders = jpaUserOrderRepository.findAll();
        model.addAttribute("allOrders", allOrders);

        return "userOrders";
    }

    @GetMapping(value = "/new")
    public String newUserOrders(Model model) {

        return "addUserOrder";
    }

    @PostMapping(value = "/new")
    public String addNewUserOrders(@RequestParam(value = "userId") Long userId,
                                   @RequestParam(value = "orderNumber") String number,
                                   Model model) {

        User user = jpaUserRepository.findOne(userId);

        UserOrder newUserOrder = new UserOrder(user, number);

        jpaUserOrderRepository.save(newUserOrder);

        return "redirect:/orders/mylist";
    }

    @GetMapping(value = "/order/{orderId}")
    public String newUserOrders(@PathVariable("orderId") Long orderId, Model model) {

        return "orderDetails";
    }
}
