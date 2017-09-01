package com.sda.Warehouse.models;

import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by mytek on 2017-09-01.
 */

@Getter
@Entity(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(value = 3)
    @Max(value = 100)
    @NotNull
    private String name;

    @Column
    @Min(value = 3)
    @Max(value = 2048)
    @NotNull
    private String description;

    @Column
    @Min(value = 3)
    @Max(value = 100)
    @NotNull
    private String location;

    @Column
    @Min(value = 0)
    @NotNull
    private Integer quantity;

    @Column
    @Min(value = 3)
    @Max(value = 1024)
    private String photo;

    @OneToOne
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

}
