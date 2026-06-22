package com.jimmy.bankapi.mapper;

import com.jimmy.bankapi.dto.response.CustomerResponse;
import com.jimmy.bankapi.entity.Customer;

import java.util.List;

public class CustomerMapper {

    public static CustomerResponse toResponse(
            Customer customer
    ) {

        return new CustomerResponse(
                customer.getId(),
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail()
        );
    }

    public static List<CustomerResponse> toResponseList(
            List<Customer> customers
    ) {

        return customers.stream()
                .map(CustomerMapper::toResponse)
                .toList();
    }
}