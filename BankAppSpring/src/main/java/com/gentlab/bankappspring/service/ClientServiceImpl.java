package com.gentlab.bankappspring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gentlab.bankappspring.model.Account;
import com.gentlab.bankappspring.model.Client;
import com.gentlab.bankappspring.repository.AccountDAO;
import com.gentlab.bankappspring.repository.ClientDAO;

@Service("clientService")
@Transactional
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDAO clientDAO;

	@Autowired
	private AccountDAO accountDAO;

	public void setClientDAO(ClientDAO clientDAO) {
		this.clientDAO = clientDAO;
	}

	public void setAccountDAO(AccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	@Override
	public void saveClient(Client client) {
		clientDAO.save(client);
	}

	@Override
	public List<Client> listClients() {
		return (List<Client>) clientDAO.findAll();
	}

	@Override
	public Client getClientById(Long id) {
		return clientDAO.findOne(id);
	}

	@Override
	public String getClientFullName(Long id) {
		Client client = getClientById(id);
		return client.getFirstname() + " " + client.getLastname();
	}

	@Override
	public void removeClient(Long id) {
		clientDAO.delete(id);
	}

	@Override
	public void saveAccount(Account account) {
		accountDAO.save(account);
	}

	@Override
	public List<Account> listAccounts() {
		return (List<Account>) accountDAO.findAll();
	}

	@Override
	public List<Account> listAccountsForClient(Long id) {
		return accountDAO.findByClientId(id);
	}

	@Override
	public Account getAccountById(Long id) {
		return accountDAO.findOne(id);
	}

	@Override
	public void removeAccount(Long id) {
		accountDAO.delete(id);
	}
}
