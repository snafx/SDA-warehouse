package com.sda.Warehouse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainController {

    @GetMapping(value = "/login")
    public String logOn() {
        return "login";
    }

    @GetMapping(value = "/home")
    public String home() {
        return "index";
    }

    @GetMapping(value = "/")
    public String home1() {
        return "index";
    }

    @GetMapping(value = "/addUser")
    public String addUser() {
        return "registration";
    }


    @GetMapping("/403")
    public String error403() {
        return "/error/403";
    }
}
