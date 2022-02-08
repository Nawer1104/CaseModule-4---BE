package com.example.CaseModule4.service.impl;

import com.example.CaseModule4.model.Category;
import com.example.CaseModule4.repository.ICategoryRepository;
import com.example.CaseModule4.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {
    @Autowired
    ICategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
