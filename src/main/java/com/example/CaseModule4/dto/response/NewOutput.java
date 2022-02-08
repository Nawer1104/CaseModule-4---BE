package com.example.CaseModule4.dto.response;

import com.example.CaseModule4.model.Orders;

import java.util.ArrayList;
import java.util.List;

public class NewOutput {
    private int page;
    private int totalPage;
    private List<Orders> orders = new ArrayList<>();

    public NewOutput() {
    }

    public NewOutput(int page, int totalPage, List<Orders> orders) {
        this.page = page;
        this.totalPage = totalPage;
        this.orders = orders;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
