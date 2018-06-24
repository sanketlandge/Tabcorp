package com.tabcorp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.tabcorp.domain.Bet;

@Repository
public interface BetRepository extends CrudRepository<Bet, Integer> {

}
