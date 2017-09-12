package com.sda.Warehouse.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Setter
@Getter
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "order_details")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id")
    private UserOrder parentOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Integer quantity;

    @Column
    private BigDecimal price;

    @Column
    private BigDecimal amount;

    public OrderDetails(UserOrder parentOrder, Product product, Integer quantity, BigDecimal price) {
        this.parentOrder = parentOrder;
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.amount = this.price.multiply(BigDecimal.valueOf(this.quantity));
    }
}
