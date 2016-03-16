package com.gentlab.bankappspring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.gentlab.bankappspring.model.Account;

@Repository("accountDao")
public interface AccountDAO extends CrudRepository<Account, Long> {

	List<Account> findByClientId(Long id);
}
