package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.OrderDetails;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.models.UserOrder;
import com.sda.Warehouse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

import static org.aspectj.runtime.internal.Conversions.doubleValue;

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

        //z sesji pobieramy "springowego usera"
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //wyszukuje "mojego" usera na podstawie usename springowego usera - bo username musi byc unikalne
        User user = jpaUserRepository.findOneByUsername(principal.getUsername());


        Iterable<UserOrder> allOrders = jpaUserOrderRepository.findByOwner(user);
        model.addAttribute("allOrders", allOrders);


        UserOrder userOrder = jpaUserOrderRepository.findOneByOwnerAndIsApprovedIsFalse(user);
        if (userOrder != null) {
            String message = "You already have an open cart. Products will be added to that cart.";
            model.addAttribute("message", message);
        }

        return "userOrders";
    }

    @GetMapping(value = "/new")
    public String newUserOrders(Model model) {

        //z sesji pobieramy "springowego usera"
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //wyszukuje "mojego" usera na podstawie usename springowego usera - bo username musi byc unikalne
        User user = jpaUserRepository.findOneByUsername(principal.getUsername());

        UserOrder userOrder = jpaUserOrderRepository.findOneByOwnerAndIsApprovedIsFalse(user);

        if (userOrder == null) {
            return "addUserOrder";
        } else {
            String message = "You already have an open cart. Products will be added to that cart.";
            model.addAttribute("message", message);
            return "redirect:/orders/mylist";
        }

    }

    @PostMapping(value = "/new")
    public String addNewUserOrders(@RequestParam(value = "userId") Long userId,
                                   @RequestParam(value = "orderNumber") String number,
                                   Model model) {

        //z sesji pobieramy "springowego usera"
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //wyszukuje "mojego" usera na podstawie usename springowego usera - bo username musi byc unikalne
        User user = jpaUserRepository.findOneByUsername(principal.getUsername());

        UserOrder newUserOrder = new UserOrder(user, number);

        jpaUserOrderRepository.save(newUserOrder);

        return "redirect:/orders/mylist";
    }

    @GetMapping(value = "/order/{orderId}")
    public String newUserOrders(@PathVariable("orderId") Long orderId, Model model) {

        UserOrder one = jpaUserOrderRepository.findOne(orderId);

        List<OrderDetails> orderDetailsList = jpaOrderDetailsRepository.findByParentOrder(one);

//        double sum = orderDetailsList.stream()
//                .mapToDouble(e -> doubleValue(e.getAmount()))
//                .sum();

        BigDecimal sum = orderDetailsList.stream()
                .map(e -> e.getAmount())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("allOrders", orderDetailsList);
        model.addAttribute("order", one);
        model.addAttribute("orderSum", sum);
        model.addAttribute("totalElements", orderDetailsList.size());

        return "orderDetails";
    }

    @PostMapping(value = "/new-detail")
    public String addNewUserOrders(@RequestParam(value = "productId") Long productId,
                                   @RequestParam(value = "quantity") Integer quantity,
                                   @RequestParam(value = "price") BigDecimal price,
                                   Model model,
                                   RedirectAttributes redir) {

        //z sesji pobieramy "springowego usera"
        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        //wyszukuje "mojego" usera na podstawie usename springowego usera - bo username musi byc unikalne
        User user = jpaUserRepository.findOneByUsername(principal.getUsername());

        UserOrder userOrder = jpaUserOrderRepository.findOneByOwnerAndIsApprovedIsFalse(user);

        Product product = jpaProductRepository.findOne(productId);

        if (quantity <= product.getQuantity()) {

            OrderDetails newOrderDetail = new OrderDetails(userOrder, product, quantity, price);
            jpaOrderDetailsRepository.save(newOrderDetail);

            product.setQuantity(product.getQuantity() - quantity);
            jpaProductRepository.save(product);
        } else {
            String message = "Chosen quantity is too big!";
            redir.addFlashAttribute("message",message);

            return "redirect:/products/product/" + productId;
        }


        return "redirect:/products";
    }

    @PostMapping(value = "/approve/{orderId}")
    public String setUserOrderApproved(@PathVariable("orderId") Long orderId, Model model) {

        UserOrder one = jpaUserOrderRepository.findOne(orderId);

        one.setIsApproved(true);
        jpaUserOrderRepository.save(one);

        return "redirect:/orders/mylist";
    }

}