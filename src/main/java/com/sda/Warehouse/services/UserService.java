package com.sda.Warehouse.services;


import com.sda.Warehouse.models.Role;
import com.sda.Warehouse.models.User;
import com.sda.Warehouse.repositories.JpaRoleRepository;
import com.sda.Warehouse.repositories.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserService {


    @Autowired
    private JpaUserRepository jpaUserRepository;


    @Autowired
    private JpaRoleRepository jpaRoleRepository;


    public void addRoleToUser(User user, Role role){

        jpaRoleRepository.save(role);
        user.getRoles().add(role);
        jpaUserRepository.save(user);

    }


}
