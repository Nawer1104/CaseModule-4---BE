package com.example.CaseModule4.service;

import com.example.CaseModule4.model.CartItem;
import com.example.CaseModule4.model.Users;

import java.util.List;

public interface IShoppingCartService {
    List<CartItem> listCartItems (Users users);
    void addProduct(Long productId, double quantity, Users users);
    void updateQuantity(double quantity, Long productId, Users users);
    void remove(Users users, Long productId);
    void removeAll(Users users);
}
