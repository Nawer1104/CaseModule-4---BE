package com.example.CaseModule4.service;

import com.example.CaseModule4.model.Orders;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IOrdersService {
    void save (Orders orders);
    List<Orders> findAll(Pageable pageable);
    List<Orders> findAllById(Long keyword);
    int totalItem();
}
