package com.rest.demo.config;

import com.customer.aerospike.AerospikePort;
import com.customer.aerospike.AerospikePortService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SOAPConfig {
    /**
     * bean for soap service
     * @return returns AerospikePort bean
     */
    @Bean
    public AerospikePort aerospikePort(){
        return new AerospikePortService().getAerospikePortSoap11();
    }
}
