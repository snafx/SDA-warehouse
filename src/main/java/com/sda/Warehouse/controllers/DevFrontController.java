package com.sda.Warehouse.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DevFrontController {

    @GetMapping(value = "/test")
    public String test() {
        return "index";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }


}
