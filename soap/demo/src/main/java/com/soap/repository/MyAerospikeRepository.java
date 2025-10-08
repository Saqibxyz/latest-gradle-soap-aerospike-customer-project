package com.soap.repository;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import com.soap.model.Customer;
import org.springframework.stereotype.Repository;
@Repository
public interface MyAerospikeRepository extends AerospikeRepository<Customer,String> {

}
