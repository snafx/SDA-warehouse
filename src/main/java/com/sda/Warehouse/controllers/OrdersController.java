package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.OrderDetails;
import com.sda.Warehouse.models.Product;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.models.UserOrder;
import com.sda.Warehouse.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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

        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Long userId = user.getId();

        User user = jpaUserRepository.findOne(Long.valueOf(2));

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

        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Long userId = user.getId();

        User user = jpaUserRepository.findOne(Long.valueOf(2));

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

        User user = jpaUserRepository.findOne(userId);

        UserOrder newUserOrder = new UserOrder(user, number);

        jpaUserOrderRepository.save(newUserOrder);

        return "redirect:/orders/mylist";
    }

    @GetMapping(value = "/order/{orderId}")
    public String newUserOrders(@PathVariable("orderId") Long orderId, Model model) {

        UserOrder one = jpaUserOrderRepository.findOne(orderId);

        List<OrderDetails> orderDetailsList = jpaOrderDetailsRepository.findByParentOrder(one);

        double sum = orderDetailsList.stream()
                .mapToDouble(e -> e.getAmount())
                .sum();

        model.addAttribute("allOrders", orderDetailsList);
        model.addAttribute("order", one);
        model.addAttribute("orderSum", sum);
        model.addAttribute("totalElements", orderDetailsList.size());

        return "orderDetails";
    }

    @PostMapping(value = "/new-detail")
    public String addNewUserOrders(@RequestParam(value = "productId") Long productId,
                                   @RequestParam(value = "quantity") Integer quantity,
                                   @RequestParam(value = "price") Double price,
                                   Model model,
                                   RedirectAttributes redir) {

        //userId z sesji
        //na podstawie usera pobierz obiekt order
        //stworz obiekt details i zapisz do bazy
        //redirect na liste produktow

        //User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Long userId = user.getId();

        User user = jpaUserRepository.findOne(Long.valueOf(2));

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
