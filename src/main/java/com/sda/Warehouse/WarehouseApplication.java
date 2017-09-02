package com.sda.Warehouse;

import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WarehouseApplication {


    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
