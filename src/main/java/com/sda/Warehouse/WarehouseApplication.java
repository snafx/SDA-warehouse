package com.sda.Warehouse;

import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class WarehouseApplication {

    @Autowired
    private JpaUserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("Jan", "Kowalski", "kowalski@wp.pl", "qwerty123", "warehouseman", false));
        userRepository.save(new User("Jan", "Nowak", "a@a.com", "a", "warehouseman", false));
    }
}
