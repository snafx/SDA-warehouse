package com.sda.Warehouse.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Entity(name = "user_order")
public class UserOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    private User owner;

    @OneToMany(mappedBy = "parentOrder", cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetailsList = new ArrayList<>();

    @Column
    private String orderNumber;

    @Column
    private LocalDate date;

    @Column
    private Double orderSum;

    @Column
    private Boolean isApproved;

    public UserOrder(User user, String number) {
        this.owner = user;
        this.orderNumber =  number;
        this.date = LocalDate.now();
        this.orderSum = 0.0;
        this.isApproved = true;
    }
}
