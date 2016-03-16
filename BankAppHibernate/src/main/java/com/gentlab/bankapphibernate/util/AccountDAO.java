package com.gentlab.bankapphibernate.util;

import java.util.List;

import org.hibernate.Session;

import com.gentlab.bankapphibernate.domain.Account;

public class AccountDAO {
	private Session session;

	public AccountDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void close() {
		session.getTransaction().commit();
	}

	public void saveAccount(Account account) {
		session.merge(account);
	}

	public void removeAccount(Account account) {
		session.delete(account);
	}

	public Account getAccount(Long id) {
		Account account = session.get(Account.class, id);
		return account;
	}

	public List getAccountsForClient(Long clientId) {
		List accounts = session.createQuery("from " + Account.class.getName() + " where client_id = " + clientId)
				.list();
		return accounts;
	}
}
