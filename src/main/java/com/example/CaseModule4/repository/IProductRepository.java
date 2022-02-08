package com.example.CaseModule4.repository;

import com.example.CaseModule4.model.IOrderDetail;
import com.example.CaseModule4.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByCategory_Id(Long id);

    @Query(nativeQuery = true, value = "SELECT products.productname, products.price, orders.created_date, orders.amount, users.name, order_detail.quantity, orders.id FROM case_module4.products\n" +
            "join order_detail on products.id = order_detail.product_id \n" +
            "join orders on order_detail.orders_id = orders.id\n" +
            "join users on orders.users_id = users.id where orders_id =:idOrder")
    List<IOrderDetail> getOrderDetail(@Param(value = "idOrder") Long idOrder);
}
