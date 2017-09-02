package com.sda.Warehouse.models;

import lombok.*;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "users")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 15)
    @NotNull
    private String firstName;

    @Size(min = 1, max = 15)
    private String lastName;

    @Email
    @Column(unique = true)
    @NotNull
    @Size(min = 7, max = 20)
    private String email;

    @Size(min = 1, max = 15)
    @NotNull
    private String password;

    @Size(min = 1, max = 15)
    @NotNull
    private String role;

    @Setter
    @NotNull
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
