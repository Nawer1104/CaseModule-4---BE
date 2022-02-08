package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.OrderDetail;
import com.example.CaseModule4.repository.IOrderDetailRepository;
import com.example.CaseModule4.service.IOrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderDetailServiceImpl implements IOrderDetailService {
    @Autowired
    IOrderDetailRepository orderDetailRepository;

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }
}
