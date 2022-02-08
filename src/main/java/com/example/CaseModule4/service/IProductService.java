package com.example.CaseModule4.service;

import com.example.CaseModule4.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    void save(Product product);
    void deleteById(Long id);
    Product findById(Long id);
    List<Product> findProductByCategory_Id(Long id);
    List<Product> findTwoMostLikedProduct();
}
