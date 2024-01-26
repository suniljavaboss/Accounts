package com.easybank.accounts.mapper;

import com.easybank.accounts.dto.CustomerDto;
import com.easybank.accounts.entity.Customer;

public class CustomerMapper {

    public static CustomerDto mapToCustomerDto(Customer customer, CustomerDto customerDto){

        customerDto.setEmail(customer.getEmail());
        customerDto.setMobileNumber(customer.getMobileNumber());
        customerDto.setName(customer.getName());

        return customerDto;
    }

    public static Customer mapToCostomer(Customer customer, CustomerDto customerDto){

        customer.setEmail(customerDto.getEmail());
        customer.setName(customerDto.getName());
        customer.setMobileNumber(customerDto.getMobileNumber());

        return customer;

    }
}
