package com.example.CaseModule4.model;

import java.util.Date;

public interface IOrderDetail {
    String getProductName();
    double getPrice();
    Date getCreated_Date();
    double getAmount();
    String getName();
    double getQuantity();
    Long getId();
}
