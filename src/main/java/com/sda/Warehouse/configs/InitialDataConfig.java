package com.sda.Warehouse.configs;

import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class InitialDataConfig {


    private JpaUserRepository userRepository;

    @Autowired
    public InitialDataConfig(JpaUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {
        userRepository.save(new User("Jan", "Kowalski", "kowalski@wp.pl", "qwerty123", "warehouseman", false));
        userRepository.save(new User("Jan", "Kowalski", "a@a.com", "a", "warehouseman", false));
        userRepository.save(new User("Marcin", "Kowalski", "abc@xyz.com", "abc", "warehouseman", false));
    }
}
