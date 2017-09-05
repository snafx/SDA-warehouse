package com.sda.Warehouse.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by mytek on 2017-09-01.
 */
@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Length.List({
            @Length(min = 1),
            @Length(max = 100)
    })
    @NotNull
    private String name;

    @Column
    @Length.List({
            @Length(min = 1),
            @Length(max = 2048)
    })
    @NotNull
    private String description;

    @Column
    @Length.List({
            @Length(min = 1),
            @Length(max = 60)
    })
    @NotNull
    private String location;

    @Column
    @Min(value = 0)
    @NotNull
    private Integer quantity;

    @Column
    @Length.List({
            @Length(min = 3),
            @Length(max = 1024)
    })
    private String photo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    @NotNull
    private Category category;

    public Product(String name, String description, String location, Integer quantity, String photo, Category category) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.quantity = quantity;
        this.photo = photo;
        this.category = category;
    }
}
