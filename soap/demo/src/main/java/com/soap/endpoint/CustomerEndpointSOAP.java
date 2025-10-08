package com.soap.endpoint;

import com.customer.aerospike.*;
import com.soap.service.AerospikeService;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
@Endpoint
public class CustomerEndpointSOAP {
    private final String NAMESPACE_URI="http://customer.com/aerospike";
    private  final AerospikeService aerospikeService;

    public CustomerEndpointSOAP(AerospikeService aerospikeService) {
        this.aerospikeService = aerospikeService;
    }
// Creating a customer
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "createCustomerRequest")
    @ResponsePayload
    public CreateCustomerResponse createCustomerRequest(@RequestPayload CreateCustomerRequest customer)
    {
        return aerospikeService.addCustomer(customer);
    }
    //Getting a customer
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getCustomerRequest")
    @ResponsePayload
    public GetCustomerResponse getCustomerRequest(@RequestPayload GetCustomerRequest request)
    {
        return aerospikeService.getCustomer(request);
    }
    //updating customer
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "updateCustomerRequest")
    @ResponsePayload
    public UpdateCustomerResponse getCustomerRequest(@RequestPayload UpdateCustomerRequest request)
    {
        return aerospikeService.updateCustomer(request);
    }
    //deleting customer
    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "deleteCustomerRequest")
    @ResponsePayload
    public DeleteCustomerResponse deleteCustomerRequest(@RequestPayload DeleteCustomerRequest request)
    {
        return aerospikeService.deleteCustomer(request);
    }
}
