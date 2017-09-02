package com.sda.Warehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/registration")
@Controller
public class RegistrationController {

    @GetMapping
    public String home() {
//        ModelAndView modelAndView = new ModelAndView("registration");
        return "registration";
    }

}
