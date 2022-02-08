package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.Product;
import com.example.CaseModule4.repository.IProductRepository;
import com.example.CaseModule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    IProductRepository productRepository;

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    @Transactional
    public void save(Product product) {
        em.joinTransaction();
        productRepository.save(product);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> findProductByCategory_Id(Long id) {
        return productRepository.findProductByCategory_Id(id);
    }

    @Override
    public List<Product> findTwoMostLikedProduct() {
        return null;
    }
}
