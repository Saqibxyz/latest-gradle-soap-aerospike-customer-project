package com.rest.demo.service;

import com.customer.aerospike.*;
import com.rest.demo.dto.CustomerDTO;
import com.rest.demo.dto.ListCreateDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static com.rest.demo.util.CustomerDTOToSOAPRequestConverter.customerDtoToCreateCustomerRequest;
import static com.rest.demo.util.CustomerDTOToSOAPRequestConverter.customerDtoToUpdateCustomerRequest;
import static com.rest.demo.util.Validator.getValidCustomerDTOs;



@Service
public class SoapClientService {
    private final AerospikePort aerospikePort;
    private final ExecutorService manyCustomerCreatorRequestExecutor;
    @Autowired
    public SoapClientService(AerospikePort aerospikePort,ExecutorService manyCustomerCreatorRequestExecutor){
       this.aerospikePort=aerospikePort;
       this.manyCustomerCreatorRequestExecutor=manyCustomerCreatorRequestExecutor;
    }

    /**
     * Methods for sending save request to SOAP web service
     * @param customerDTO customerDTO object
     * @return returns response sent by SOAP web service
     */
    public String createCustomer(CustomerDTO customerDTO) {
        CreateCustomerRequest request= customerDtoToCreateCustomerRequest(customerDTO);
        CreateCustomerResponse response= aerospikePort.createCustomer(request);
        return response.getStatus();
    }

    /**
     * get request to SOAP web service
     * @param id id of the customer
     * @return returns Customer with corresponding id if present
     */
    public CustomerDTO getCustomerResponse(String id)
    {
        GetCustomerRequest request=new GetCustomerRequest();
        request.setId(id);
        GetCustomerResponse response= aerospikePort.getCustomer(request);
        return new CustomerDTO(response.getId(),response.getName(),response.getAge(),response.getResidence(),response.getBalance());
    }

    /**
     * delete request to SOAP web service
     * @param id id of the customer
     * @return returns status send by the SOAP web service
     */
    public String deleteCustomer(String id){
        DeleteCustomerRequest deleteCustomerRequest=new DeleteCustomerRequest();
        deleteCustomerRequest.setId(id);
        DeleteCustomerResponse result= aerospikePort.deleteCustomer(deleteCustomerRequest);
      return result.getStatus();
    }

    /**
     * Update customer
     * @param customerDTO customerDTO
     * @return returns Status sent by SOAP service
     */
    public String updateCustomer(CustomerDTO customerDTO){
        UpdateCustomerRequest  updateCustomerRequest = customerDtoToUpdateCustomerRequest(customerDTO);
        UpdateCustomerResponse response= aerospikePort.updateCustomer(updateCustomerRequest);
        return response.getStatus();
    }

    /**
     * For creating many customers request to soap
     * @param customerDTOList list of CustomerDTO
     * @return returns a list of ListCreateDTO that were successfully added to aerospike
     */
    public List<ListCreateDTO> createManyCustomers(List<CustomerDTO> customerDTOList)  {
        List<Future<ListCreateDTO>> futures=new ArrayList<>();
        //get valid CustomerDTOList
        List<CustomerDTO> validCustomerDtoList=getValidCustomerDTOs(customerDTOList);
        if (validCustomerDtoList.isEmpty()) return Collections.emptyList();
        for (CustomerDTO customer:validCustomerDtoList) {
            // valid customerDTOs submitted for SOAP request
            Future<ListCreateDTO> res= manyCustomerCreatorRequestExecutor.submit(() -> {
                CreateCustomerRequest createCustomerRequest= customerDtoToCreateCustomerRequest(customer);
                CreateCustomerResponse response= aerospikePort.createCustomer(createCustomerRequest);
                ListCreateDTO listCreateDTO= new ListCreateDTO();
                listCreateDTO.setId(customer.getId());
                listCreateDTO.setStatus(response.getStatus());
                return listCreateDTO;
            });
            futures.add(res);
        }
        List<ListCreateDTO> result=new ArrayList<>(); // for storing response of threads
        for(Future<ListCreateDTO> future:futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException ex  ) {
               //TODO : Log this exception
                System.out.println(ex.getMessage());
            }
        }
        return result;
    }
}
