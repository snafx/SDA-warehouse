package com.sda.Warehouse.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "bookAuthor")
public class BookAuthor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "bookAuthor", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public BookAuthor(String name) {
        this.name = name;
    }
}
