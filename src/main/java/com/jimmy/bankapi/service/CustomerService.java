package com.jimmy.bankapi.service;

import com.jimmy.bankapi.dto.request.CreateCustomerRequest;
import com.jimmy.bankapi.entity.Customer;
import com.jimmy.bankapi.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import com.jimmy.bankapi.dto.response.CustomerResponse;
import com.jimmy.bankapi.mapper.CustomerMapper;

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

    public List<CustomerResponse> getAllCustomers() {

        return CustomerMapper.toResponseList(
                customerRepository.findAll()
        );
    }
    public CustomerResponse getCustomerById(Long id) {

        Customer customer =
                customerRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found: " + id
                                ));

        return CustomerMapper.toResponse(
                customer
        );
    }
}