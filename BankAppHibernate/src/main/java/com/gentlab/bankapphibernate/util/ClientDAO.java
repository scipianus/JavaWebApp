package com.gentlab.bankapphibernate.util;

import java.util.List;

import org.hibernate.Session;

import com.gentlab.bankapphibernate.domain.Client;

public class ClientDAO {

	private Session session;

	public ClientDAO() {
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
	}

	public void close() {
		session.getTransaction().commit();
	}

	public void saveClient(Client client) {
		session.merge(client);
	}

	public void removeClient(Client client) {
		session.delete(client);
	}

	public Client getClient(Long id) {
		Client client = session.get(Client.class, id);
		return client;
	}

	public List getClients() {
		List clients = session.createQuery("from " + Client.class.getName()).list();
		return clients;
	}
}
