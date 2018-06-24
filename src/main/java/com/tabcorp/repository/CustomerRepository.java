package com.tabcorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tabcorp.domain.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
