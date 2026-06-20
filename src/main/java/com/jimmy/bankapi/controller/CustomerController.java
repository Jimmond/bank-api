package com.jimmy.bankapi.controller;

import com.jimmy.bankapi.dto.CreateCustomerRequest;
import com.jimmy.bankapi.entity.Customer;
import com.jimmy.bankapi.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(
            CustomerService customerService
    ) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer createCustomer(
            @RequestBody CreateCustomerRequest request
    ) {
        return customerService.createCustomer(request);
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }
}