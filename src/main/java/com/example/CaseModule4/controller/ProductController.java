package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.response.ResponseMessage;
import com.example.CaseModule4.model.Category;
import com.example.CaseModule4.model.Product;
import com.example.CaseModule4.model.Users;
import com.example.CaseModule4.service.ICategoryService;
import com.example.CaseModule4.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("products")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @GetMapping("/list")
    public List<Product> listAll () {
        return productService.findAll();
    }

    @GetMapping("/category")
    public List<Category> listAllCategory () {
        return categoryService.findAll();
    }

    @GetMapping("/list/{id}")
    public List<Product> findByCategory_Id(@PathVariable Long id) {
        return productService.findProductByCategory_Id(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBeverage(@PathVariable Long id){
        Product product = productService.findById(id);
        productService.deleteById(product.getId());
        return new ResponseEntity<>(new ResponseMessage("Delete Success!"), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBeverage(@PathVariable Long id){
        Product product = productService.findById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/save")
    public ResponseEntity<?> editBeverage(@RequestBody Product product){
        if (product.getImg().equals("")) {
            product.setImg("https://firebasestorage.googleapis.com/v0/b/casemodule4-b9134.appspot.com/o/images%2Fcore-image%2F10-104834_coffee-cup-icon-png-transparent-png.png?alt=media&token=718bb629-0e71-4741-85c7-6ed3430c9f0c");
        }
        productService.save(product);
        return new ResponseEntity<>(new ResponseMessage("Success!"), HttpStatus.OK);
    }
}
