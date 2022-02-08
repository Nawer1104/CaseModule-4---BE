package com.example.CaseModule4.repository;

import com.example.CaseModule4.model.IOrderDetail;
import com.example.CaseModule4.model.InCome;
import com.example.CaseModule4.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM case_module4.orders order by id desc;")
    @Modifying
    List<Orders> findAllDesc();


    @Query(nativeQuery = true, value = "Select orders.id, orders.created_date, orders.amount, users.name from orders \n" +
            "join users on orders.users_id = users.id\n" +
            "where created_date >= :fromDate and created_date <= :toDate group by orders.id;")
    List<InCome> getIncome(@Param(value = "fromDate") Date fromDate,
                           @Param(value = "toDate") Date toDate);
}
