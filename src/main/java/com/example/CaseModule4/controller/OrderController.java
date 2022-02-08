package com.example.CaseModule4.controller;

import com.example.CaseModule4.dto.request.IncomeForm;
import com.example.CaseModule4.dto.response.NewOutput;
import com.example.CaseModule4.model.*;
import com.example.CaseModule4.repository.IOrdersRepository;
import com.example.CaseModule4.repository.IProductRepository;
import com.example.CaseModule4.security.userprincal.UserPrinciple;
import com.example.CaseModule4.service.IOrderDetailService;
import com.example.CaseModule4.service.IOrdersService;
import com.example.CaseModule4.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    IUserService userService;

    @Autowired
    IOrdersService ordersService;

    @Autowired
    IOrderDetailService orderDetailService;

    @Autowired
    IProductRepository productRepository;

    @Autowired
    IOrdersRepository ordersRepository;

    @GetMapping("/orderdetail/{page}/{limit}")
    public ResponseEntity<?> getOrders(
            @PathVariable("page") int page,
            @PathVariable("limit") int limit
    ) {
        NewOutput result = new NewOutput();
        result.setPage(page);
        int totalPage = ordersService.totalItem() / limit;
        if (ordersService.totalItem() % limit != 0) {
            totalPage++;
        }
        result.setTotalPage(totalPage);
        Sort sort = Sort.by("id").descending();
        Pageable pageable = PageRequest.of(page - 1, limit, sort);
        result.setOrders(ordersService.findAll(pageable));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/showorderdetail/{idOrder}")
    public ResponseEntity<?> checkOrder(@PathVariable("idOrder") Long idOrder){
        List<IOrderDetail> list = productRepository.getOrderDetail(idOrder);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<?> searchOrder(@PathVariable("keyword") Long keyWord){
       List<Orders> list = ordersService.findAllById(keyWord);
       return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/checkIncome")
    public ResponseEntity<?> checkIncome(@Valid @RequestBody IncomeForm incomeForm){
        Date fromDate = incomeForm.getFromDate();
        Date toDate = incomeForm.getToDate();
        List<InCome> list = ordersRepository.getIncome(fromDate, toDate);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
