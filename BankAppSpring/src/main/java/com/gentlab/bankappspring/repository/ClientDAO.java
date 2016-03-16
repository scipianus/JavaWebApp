package com.gentlab.bankappspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gentlab.bankappspring.model.Client;

@Repository("clientDao")
public interface ClientDAO extends CrudRepository<Client, Long> {

}