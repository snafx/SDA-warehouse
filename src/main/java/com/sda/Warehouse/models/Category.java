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
@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Min(value = 3)
    @Max(value = 100)
    @NotNull
    private String name;

}
