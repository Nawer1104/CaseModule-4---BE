package com.example.CaseModule4.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Orders orders;

    @ManyToOne
    private Product product;

    private double quantity;

    public OrderDetail(Long id, Orders orders, Product product, double quantity) {
        this.id = id;
        this.orders = orders;
        this.product = product;
        this.quantity = quantity;
    }

    public OrderDetail() {
    }
}
