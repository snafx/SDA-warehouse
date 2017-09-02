package com.sda.Warehouse.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by mytek on 2017-09-01.
 */

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @OneToMany(mappedBy = "category", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }
}
