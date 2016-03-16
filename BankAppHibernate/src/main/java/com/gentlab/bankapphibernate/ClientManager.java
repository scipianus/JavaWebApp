package com.gentlab.bankapphibernate;

import java.util.List;

import com.gentlab.bankapphibernate.domain.Account;
import com.gentlab.bankapphibernate.domain.Client;
import com.gentlab.bankapphibernate.util.AccountDAO;
import com.gentlab.bankapphibernate.util.ClientDAO;

public class ClientManager {

	public static List getAllClients() {
		ClientDAO dao = new ClientDAO();
		List clients = dao.getClients();
		dao.close();
		return clients;
	}

	public static Client getClient(Long clientId) {
		ClientDAO dao = new ClientDAO();
		Client client = dao.getClient(clientId);
		dao.close();
		return client;
	}

	public static String getClientFullName(Long clientId) {
		Client client = getClient(clientId);
		return client.getFirstname() + " " + client.getLastname();
	}

	public static List getAccounts(Long clientId) {
		AccountDAO dao = new AccountDAO();
		List accounts = dao.getAccountsForClient(clientId);
		dao.close();
		return accounts;
	}

	public static Account getAccount(Long accountId) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.getAccount(accountId);
		dao.close();
		return account;
	}

	public static void saveAccount(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.saveAccount(account);
		dao.close();
	}

	public static void removeAccount(Account account) {
		AccountDAO dao = new AccountDAO();
		dao.removeAccount(account);
		dao.close();
	}
}
