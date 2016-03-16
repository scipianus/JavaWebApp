package com.gentlab.bankapp.models;

import java.util.HashMap;
import java.util.Random;

public class ClientList {
	static HashMap<Integer, Client> clientList = new HashMap<>();

	private ClientList() {

	}

	public static HashMap<Integer, Client> getList() {
		return clientList;
	}

	public static void init() {
		Random random = new Random();
		int count = random.nextInt(10) + 1;
		for (int i = 1; i <= count; ++i) {
			Client client = new Client("John", "Doe " + i);
			int nrAccounts = random.nextInt(8) + 2;
			for (int j = 1; j <= nrAccounts; ++j) {
				if (random.nextInt(2) == 1) {
					CurrentAccount account = new CurrentAccount(random.nextDouble() * 10000.0,
							random.nextDouble() * 20.0);
					client.addAccount(account);
				} else {
					SavingsAccount account = new SavingsAccount(random.nextDouble() * 10000.0,
							random.nextDouble() * 10.0);
					client.addAccount(account);
				}
			}
			clientList.put(client.getID(), client);
		}
	}

	public static int getNewId() {
		return BankAccount.getNewID();
	}

	public static Client getClient(int clientID) {
		return clientList.get(clientID);
	}

	public static String getClientFullName(int clientID) {
		Client client = getClient(clientID);
		return client.getFullName();
	}

	public static HashMap<Integer, BankAccount> getAccounts(int clientID) {
		Client client = getClient(clientID);
		return client.getAccounts();
	}

	public static BankAccount getAccount(int clientID, int accountID) {
		HashMap<Integer, BankAccount> accounts = getAccounts(clientID);
		return accounts.get(accountID);
	}

	public static void addAccount(int clientID, BankAccount account) {
		Client client = getClient(clientID);
		HashMap<Integer, BankAccount> accounts = getAccounts(clientID);
		if (accounts.containsKey(account.getID()))
			return;
		client.addAccount(account);
	}

	public static void updateAccount(int clientID, BankAccount account) {
		Client client = getClient(clientID);
		HashMap<Integer, BankAccount> accounts = getAccounts(clientID);
		if (accounts.containsKey(account.getID()) == false)
			return;
		client.updateAccount(account);
	}

	public static void removeAccount(int clientID, BankAccount account) {
		Client client = getClient(clientID);
		HashMap<Integer, BankAccount> accounts = getAccounts(clientID);
		if (accounts.containsKey(account.getID()) == false)
			return;
		client.removeAccount(account);
	}
}
