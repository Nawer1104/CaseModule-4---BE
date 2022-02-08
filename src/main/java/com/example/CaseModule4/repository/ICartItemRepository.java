package com.example.CaseModule4.repository;

import com.example.CaseModule4.model.CartItem;
import com.example.CaseModule4.model.Product;
import com.example.CaseModule4.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ICartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findCartItemByUsers(Users users);

    CartItem findCartItemsByUsersAndProduct(Users users, Product product);

    @Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id = ?2 AND c.users.id = ?3")
    @Modifying
    void updateQuantity(double quantity, Long productId, Long userId);

    @Query("DELETE FROM CartItem c where c.users.id = ?1 and c.product.id = ?2")
    @Modifying
    void deleteCartItemByEmployeeAndProduct(Long userId, Long productId);


    void deleteAllByUsers(Users users);
}
