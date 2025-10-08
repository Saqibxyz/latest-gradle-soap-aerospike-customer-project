package com.soap.service;


import com.customer.aerospike.*;
import com.soap.exceptions.UserAlreadyExistsException;
import com.soap.exceptions.UserNotFoundException;
import com.soap.model.Customer;
import com.soap.repository.MyAerospikeRepository;
import org.springframework.stereotype.Service;

@Service
public class AerospikeService {
private final MyAerospikeRepository myAerospikeRepository;
    public AerospikeService(MyAerospikeRepository myAerospikeRepository) {
        this.myAerospikeRepository = myAerospikeRepository;
    }

    /**
     * adds a customer to aerospike database
     * @param createCustomerRequest CreateCustomerRequest object
     * @return returns CreateCustomerResponse object
     */
    public CreateCustomerResponse addCustomer(CreateCustomerRequest createCustomerRequest){
        if(myAerospikeRepository.existsById(createCustomerRequest.getId()))
        {
            throw new UserAlreadyExistsException("User Already Exists");
        }
        Customer customer=new Customer(createCustomerRequest.getId(),createCustomerRequest.getName(),createCustomerRequest.getAge(),createCustomerRequest.getResidence(),createCustomerRequest.getBalance());
        myAerospikeRepository.save(customer);
        CreateCustomerResponse createCustomerResponse=new CreateCustomerResponse();
        createCustomerResponse.setStatus("CREATED");
        return createCustomerResponse;
    }

    /**
     * Returns customer with given id if exists in aerospike db
     * @param getCustomerRequest GetCustomerRequest object
     * @return returns GetCustomerResponse
     */
    public GetCustomerResponse getCustomer(GetCustomerRequest getCustomerRequest){
        Customer customer= myAerospikeRepository.findById(getCustomerRequest.getId()).orElseThrow(()->new UserNotFoundException("User not found with key: "+getCustomerRequest.getId()));
        GetCustomerResponse responseForRequest= new GetCustomerResponse();
        responseForRequest.setId(customer.getId());
        responseForRequest.setName(customer.getName());
        responseForRequest.setAge(customer.getAge());
        responseForRequest.setBalance(customer.getBalance());
        responseForRequest.setResidence(customer.getResidence());
        return responseForRequest;
    }

    /**
     * Deletes a customer from aerospike db
     * @param deleteCustomerRequest DeleteCustomerRequest
     * @return DeleteCustomerResponse
     */
    public DeleteCustomerResponse deleteCustomer(DeleteCustomerRequest deleteCustomerRequest)
    {
        String id=deleteCustomerRequest.getId();
        DeleteCustomerResponse deleteCustomerResponse=new DeleteCustomerResponse();
        if(!myAerospikeRepository.existsById(id))
        {
            deleteCustomerResponse.setStatus("Id not found or already deleted");
            return deleteCustomerResponse;
        }
        myAerospikeRepository.deleteById(id);
        deleteCustomerResponse.setStatus("DELETED");
        return deleteCustomerResponse;
    }

    /**
     * Updates a customer record in aerospike db
     * @param updateCustomerRequest update request object
     * @return returns UpdateCustomerResponse
     */
    public UpdateCustomerResponse updateCustomer(UpdateCustomerRequest updateCustomerRequest)
    {
        Customer customer=myAerospikeRepository.findById(updateCustomerRequest.getId()).orElseThrow(()->new UserNotFoundException("Cannot find user with id "+updateCustomerRequest.getId()));
        customer.setAge(updateCustomerRequest.getAge());
        customer.setBalance(updateCustomerRequest.getBalance());
        customer.setName(updateCustomerRequest.getName());
        customer.setResidence(updateCustomerRequest.getResidence());
        UpdateCustomerResponse updateCustomerResponse=new UpdateCustomerResponse();
        myAerospikeRepository.save(customer);
        updateCustomerResponse.setStatus("SUCCESS");
        return updateCustomerResponse;
    }

}
