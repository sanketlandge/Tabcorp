package com.tabcorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tabcorp.domain.ApiUser;

@Repository
public interface ApiUserRepository extends CrudRepository<ApiUser, Integer> {

	ApiUser findByUsername(String username);

}
