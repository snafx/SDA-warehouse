package com.sda.Warehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping
    public String logOn() {
        return "login";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/addUser")
    public String addUser() {
        return "registration";
    }
}
