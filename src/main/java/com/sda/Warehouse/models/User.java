package com.sda.Warehouse.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 15)
    private String firstName;

    @Size(min = 1, max = 15)
    private String lastName;

    @Email
    @Column(unique = true)
    private String email;

    @Size(min = 1, max = 15)
    private String password;

    @Size(min = 1, max = 15)
    private String role;

    private boolean isActive;

    public User(String firstName, String lastName, String email, String password, String role, boolean isActive) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
        this.isActive = isActive;
    }
}
