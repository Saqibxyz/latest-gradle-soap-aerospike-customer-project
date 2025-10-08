package com.rest.demo.controller;
import com.rest.demo.dto.CustomerDTO;
import com.rest.demo.dto.ListCreateDTO;
import com.rest.demo.service.SoapClientService;
import com.sun.istack.NotNull;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
public class CustomerController {
    private final SoapClientService soapClientService;
    public CustomerController(SoapClientService soapClientService)
    {
        this.soapClientService=soapClientService;
    }
    @PostMapping("/customer/create")
    public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerDTO customerDTO)
    {
        String message= soapClientService.createCustomer(customerDTO);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
    @PostMapping("/customer/createMany")
    public ResponseEntity<List<ListCreateDTO>> createManyCustomers(@RequestBody List<CustomerDTO> customerDTOList){
        List<ListCreateDTO> resp= soapClientService.createManyCustomers(customerDTOList);
        return new ResponseEntity<>(resp,HttpStatus.CREATED);
    }
    @GetMapping("/customer/{id}")
    public CustomerDTO getCustomerResponse(@PathVariable @NotNull String id)
    {
        return soapClientService.getCustomerResponse(id);
    }
    @DeleteMapping("/customer/delete/{id}")
    public String deleteCustomer(@PathVariable @NotNull String id)
    {
        return  soapClientService.deleteCustomer(id);
    }
    @PutMapping("/customer/update")
    public String updateCustomer(@RequestBody @Valid CustomerDTO customerDTO)
    {
        return soapClientService.updateCustomer(customerDTO);
    }
}
