package com.rest.demo.util;

import com.customer.aerospike.CreateCustomerRequest;
import com.customer.aerospike.UpdateCustomerRequest;
import com.rest.demo.dto.CustomerDTO;

public class CustomerDTOToSOAPRequestConverter {
    /**
     * converts CustomerDTO object to CreateCustomerRequest object
     * @param customer customerDTO object
     * @return returns CreateCustomerRequest object
     */
   public static CreateCustomerRequest customerDtoToCreateCustomerRequest(CustomerDTO customer){
        CreateCustomerRequest createCustomerRequest=new CreateCustomerRequest();
        createCustomerRequest.setId(customer.getId());
        createCustomerRequest.setName(customer.getName());
        createCustomerRequest.setAge(customer.getAge());
        createCustomerRequest.setResidence(customer.getResidence());
        createCustomerRequest.setBalance(customer.getBalance());
        return createCustomerRequest;
    }
    /**
     * converts CustomerDTO object to UpdateCustomerRequest object
     * @param customerDTO customerDTO object
     * @return returns UpdateCustomerRequest object
     */
   public static UpdateCustomerRequest customerDtoToUpdateCustomerRequest(CustomerDTO customerDTO){
        UpdateCustomerRequest updateCustomerRequest=new UpdateCustomerRequest();
        updateCustomerRequest.setId(customerDTO.getId());
        updateCustomerRequest.setName(customerDTO.getName());
        updateCustomerRequest.setAge(customerDTO.getAge());
        updateCustomerRequest.setBalance(customerDTO.getBalance());
        updateCustomerRequest.setResidence(customerDTO.getResidence());
        return updateCustomerRequest;
    }
}
