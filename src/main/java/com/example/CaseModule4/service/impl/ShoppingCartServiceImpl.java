package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.CartItem;
import com.example.CaseModule4.model.Product;
import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.repository.ICartItemRepository;
import com.example.CaseModule4.service.IProductService;
import com.example.CaseModule4.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {
    @Autowired
    ICartItemRepository cartItemRepository;

    @Autowired
    IProductService productService;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<CartItem> listCartItems(Users users) {
        return cartItemRepository.findCartItemByUsers(users);
    }

    @Override
    public void addProduct(Long productId, double quantity, Users users) {
        double addedQuantity = quantity;
        Product product = productService.findById(productId);
        CartItem cartItem = cartItemRepository.findCartItemsByUsersAndProduct(users, product);
        if (cartItem!= null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
            cartItem.setProduct(product);
            cartItem.setUsers(users);
        }
        cartItemRepository.save(cartItem);
    }

    @Override
    @Transactional
    public void updateQuantity(double quantity, Long productId, Users users) {
        em.joinTransaction();
        cartItemRepository.updateQuantity(quantity, productId, users.getId());
    }

    @Override
    @Transactional
    public void remove(Users users, Long productId) {
        em.joinTransaction();
        cartItemRepository.deleteCartItemByEmployeeAndProduct(users.getId(), productId);
    }

    @Override
    @Transactional
    public void removeAll(Users users) {
        em.joinTransaction();
        cartItemRepository.deleteAllByUsers(users);
    }
}
