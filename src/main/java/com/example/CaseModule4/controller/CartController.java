package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.response.ResponseMessage;
import com.example.CaseModule4.model.*;
import com.example.CaseModule4.repository.IProductRepository;
import com.example.CaseModule4.security.userprincal.UserPrinciple;
import com.example.CaseModule4.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/cart")
public class CartController {
    @Autowired
    IUserService userService;

    @Autowired
    IShoppingCartService shoppingCartService;

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IProductService productService;

    @GetMapping("/list")
    public ResponseEntity<?> showShoppingCart(@AuthenticationPrincipal UserPrinciple userPrinciple) {
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        List<CartItem> list = shoppingCartService.listCartItems(users);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/add/{pid}")
    public ResponseEntity<?> addProductToCart(
            @PathVariable("pid") Long productId,
            @AuthenticationPrincipal UserPrinciple userPrinciple
    ){
        double quantity = 1;
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        shoppingCartService.addProduct(productId, quantity, users);
        return showShoppingCart(userPrinciple);
    }

    @GetMapping("/update/{pid}/{qty}")
    public ResponseEntity<?> updateProductQuantity(
            @PathVariable("qty") double quantity,
            @PathVariable("pid") Long productId,
            @AuthenticationPrincipal UserPrinciple userPrinciple
    ){
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        if (quantity > 0) {
            shoppingCartService.updateQuantity(quantity, productId, users);
            return showShoppingCart(userPrinciple);
        } else {
            shoppingCartService.remove(users, productId);
            return showShoppingCart(userPrinciple);
        }
    }

    @GetMapping("/coupon/{coupon}")
    public ResponseEntity<?> checkCoupon(
            @PathVariable("coupon") String coupon,
            @AuthenticationPrincipal UserPrinciple userPrinciple
    ){
        if (coupon.equals("namdeptrai")) {
            return showShoppingCart(userPrinciple);
        } else {
            return ResponseEntity.badRequest().body(new ResponseMessage("wrong_code"));
        }
    }

    @GetMapping("/checkout/{totalAmount}")
    public ResponseEntity<?> checkOutOrder(
            @PathVariable("totalAmount")  double totalAmount,
            @AuthenticationPrincipal UserPrinciple userPrinciple
    ){
        Users users = userService.findByUserName(userPrinciple.getUsername()).get();
        List<CartItem> list = shoppingCartService.listCartItems(users);
        Orders orders = new Orders();
        orders.setCreatedDate(new Date());
        orders.setUsers(users);
        orders.setAmount(totalAmount);
        ordersService.save(orders);
        for (CartItem x : list) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrders(orders);
            orderDetail.setProduct(x.getProduct());
            orderDetail.setQuantity(x.getQuantity());
            orderDetailService.save(orderDetail);
            Product product = x.getProduct();
            product.setLiked((int) (product.getLiked() + x.getQuantity()));
            productService.save(product);
        }
        shoppingCartService.removeAll(users);
        return showShoppingCart(userPrinciple);
    }
}
