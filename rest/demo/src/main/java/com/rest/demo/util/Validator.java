package com.rest.demo.util;

import com.rest.demo.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class Validator {
    /**
     * filter valid DTOs
     * @param customerDTO List of customerDTO
     * @return returns valid DTOs from the list
     */
    public static List<CustomerDTO> getValidCustomerDTOs(List<CustomerDTO> customerDTO) {
        List<CustomerDTO> res=new ArrayList<>();
        for(CustomerDTO customerDTO1:customerDTO)
        {
            String id=customerDTO1.getId();
            String name=customerDTO1.getName();
            String residence=customerDTO1.getResidence();
            if(id==null || name==null || residence==null || id.trim().isEmpty() || name.trim().isEmpty() || residence.trim().isEmpty())
            {
                continue;
            }
            res.add(customerDTO1);
        }

       return res;
    }
}
