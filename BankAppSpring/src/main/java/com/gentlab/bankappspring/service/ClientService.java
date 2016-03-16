package com.gentlab.bankappspring.service;

import java.util.List;

import com.gentlab.bankappspring.model.Account;
import com.gentlab.bankappspring.model.Client;

public interface ClientService {
	public void saveClient(Client client);

	public List<Client> listClients();

	public Client getClientById(Long id);

	public String getClientFullName(Long id);

	public void removeClient(Long id);

	public void saveAccount(Account account);

	public List<Account> listAccounts();

	public List<Account> listAccountsForClient(Long id);

	public Account getAccountById(Long id);

	public void removeAccount(Long id);
}
