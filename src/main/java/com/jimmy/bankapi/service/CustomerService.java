package com.jimmy.bankapi.service;

import com.jimmy.bankapi.dto.CreateCustomerRequest;
import com.jimmy.bankapi.entity.Customer;
import com.jimmy.bankapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(
            CustomerRepository customerRepository
    ) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(
            CreateCustomerRequest request
    ) {

        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
}