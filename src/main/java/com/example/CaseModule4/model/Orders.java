package com.example.CaseModule4.model;

import lombok.Data;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+7")
    private Date createdDate;

    @ManyToOne
    private Users users;

    private double amount;

    public Orders(Long id, Date createdDate, Users users, double amount) {
        this.id = id;
        this.createdDate = createdDate;
        this.users = users;
        this.amount = amount;
    }

    public Orders() {
    }
}
