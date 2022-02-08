package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.Orders;
import com.example.CaseModule4.repository.IOrdersRepository;
import com.example.CaseModule4.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrdersServiceImpl implements IOrdersService {
    @Autowired
    IOrdersRepository ordersRepository;

    @Override
    public void save(Orders orders) {
        ordersRepository.save(orders);
    }

    @Override
    public List<Orders> findAll(Pageable pageable) {
        return ordersRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Orders> findAllById(Long keyWord) {
        return ordersRepository.findAllById(Collections.singleton(keyWord));
    }

    @Override
    public int totalItem() {
        return (int) ordersRepository.count();
    }
}
