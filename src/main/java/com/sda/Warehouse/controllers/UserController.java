package com.sda.Warehouse.controllers;

import com.sda.Warehouse.models.CreationStatus;
import com.sda.Warehouse.models.CreationStatusFactory;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by strzalkom on 04.09.2017.
 */


@Controller
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private JpaUserRepository userRepository;

    @Autowired
    private CreationStatusFactory creationStatusFactory;

    @GetMapping
    public ModelAndView users() {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userRepository.findAll());
        return modelAndView;
    }

    @GetMapping(params = {"lastName"})
    public ModelAndView usersByLastName(@RequestParam String lastName) {
        ModelAndView modelAndView = new ModelAndView("users");
        modelAndView.addObject("users", userRepository.findByLastName(lastName));
        return modelAndView;
    }

    @GetMapping(path = "/{id}")
    public ModelAndView specifiedUser(@PathVariable("id") Long userId) {
        User user = userRepository.findOne(userId);
        ModelAndView modelAndView = new ModelAndView("user");
        modelAndView.addObject("user", user);

        return modelAndView;
    }


    @PostMapping
    public ModelAndView addUser(@ModelAttribute User user) {
        userRepository.save(user);
        ModelAndView modelAndView = users();
        CreationStatus creationStatus = true ?
                creationStatusFactory.createSuccessStatus("Successfully created user.") :
                creationStatusFactory.createFailureStatus("Couldn't create user");
        modelAndView.addObject("creationStatus", creationStatus);
        return modelAndView;

    }


    @PostMapping(path = "/{id}/delete")
    public String removeUser(@PathVariable("id") Long userid) {
        userRepository.delete(userid);
        return "redirect:/users/";
    }


}
