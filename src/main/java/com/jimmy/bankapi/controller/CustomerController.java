package com.jimmy.bankapi.controller;

import com.jimmy.bankapi.dto.request.CreateCustomerRequest;
import com.jimmy.bankapi.dto.response.CustomerResponse;
import com.jimmy.bankapi.entity.Customer;
import com.jimmy.bankapi.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

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
            @Valid
            @RequestBody CreateCustomerRequest request
    ) {
        return customerService.createCustomer(request);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<CustomerResponse> getAllCustomers() {
        return customerService.getAllCustomers();
    }
    @GetMapping("/{id}")
    public CustomerResponse getCustomerById(
            @PathVariable Long id
    ) {

        return customerService.getCustomerById(id);
    }
}