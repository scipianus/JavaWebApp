package com.gentlab.bankappspring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gentlab.bankappspring.model.Account;
import com.gentlab.bankappspring.model.AccountTypes;
import com.gentlab.bankappspring.model.CurrentAccount;
import com.gentlab.bankappspring.model.RequestTypes;
import com.gentlab.bankappspring.model.SavingsAccount;
import com.gentlab.bankappspring.service.ClientService;

@Controller
public class BankController {

	@Autowired
	private ClientService clientService;

	public void setClientService(ClientService clientService) {
		this.clientService = clientService;
	}

	@RequestMapping(value = "/clients", method = RequestMethod.GET)
	public String listClients(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("clientList", clientService.listClients());
		return "clients";
	}

	@RequestMapping(value = "/clients", method = RequestMethod.POST)
	public String selectClient(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Long clientId = Long.parseLong(request.getParameter("clientId"));
		session.setAttribute("clientId", clientId);
		return "redirect:/app/accounts";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.GET)
	public String listAccounts(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		Long clientId = (Long) session.getAttribute("clientId");
		String clientFullName = clientService.getClientFullName(clientId);
		request.setAttribute("accountList", clientService.listAccountsForClient(clientId));
		request.setAttribute("clientName", clientFullName);
		return "accounts";
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST)
	public String selectAccount(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();

		String requestTypeString = request.getParameter("requestType");
		RequestTypes requestType = RequestTypes.fromString(requestTypeString);
		session.setAttribute("requestType", requestType);

		if (requestType != RequestTypes.CREATE) {
			Long accountId = Long.parseLong(request.getParameter("accountId"));
			session.setAttribute("accountId", accountId);

			Account account = clientService.getAccountById(accountId);
			session.setAttribute("accountType", account.getType());
		} else {
			session.setAttribute("accountId", null);

			String accountTypeString = request.getParameter("accountType");
			AccountTypes accountType = AccountTypes.fromString(accountTypeString);
			session.setAttribute("accountType", accountType);
		}

		return "redirect:/app/account_details";
	}

	@RequestMapping(value = "/account_details", method = RequestMethod.GET)
	public String listAccountDetails(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		RequestTypes requestType = (RequestTypes) session.getAttribute("requestType");
		Long accountId;
		AccountTypes accountType = (AccountTypes) session.getAttribute("accountType");
		if (requestType == RequestTypes.CREATE) {

		} else {
			accountId = (Long) session.getAttribute("accountId");
			request.setAttribute("account", clientService.getAccountById(accountId));
		}
		request.setAttribute("accountType", accountType);
		request.setAttribute("requestType", requestType);
		return "accountDetails";
	}

	@RequestMapping(value = "/account_details", method = RequestMethod.POST)
	public String submitAccountRequest(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String requestTypeString = request.getParameter("requestType");
		RequestTypes requestType = RequestTypes.fromString(requestTypeString);

		Account account = getAccountFromPost(request);
		Long accountId = (Long) session.getAttribute("accountId");

		if (requestType == RequestTypes.CREATE || requestType == RequestTypes.UPDATE)
			clientService.saveAccount(account);
		else if (requestType == RequestTypes.DELETE)
			clientService.removeAccount(accountId);

		return "redirect:/app/accounts";
	}

	private Account getAccountFromPost(HttpServletRequest request) {

		String accountTypeString = request.getParameter("accountType");
		AccountTypes accountType = AccountTypes.fromString(accountTypeString);

		Account account = null;

		if (accountType == AccountTypes.CURRENT) {
			account = getCurrentAccountFromPost(request);
		} else if (accountType == AccountTypes.SAVINGS) {
			account = getSavingsAccountFromPost(request);
		}
		return account;
	}

	private Account getCurrentAccountFromPost(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Long accountId = (Long) session.getAttribute("accountId");
		Long clientId = (Long) session.getAttribute("clientId");

		CurrentAccount account = new CurrentAccount();

		if (accountId != null)
			account.setId(accountId);
		account.setClientId(clientId);

		String valueString = request.getParameter("accountValue");
		double value = Double.parseDouble(valueString);
		account.setValue(value);

		String commissionString = request.getParameter("commissionPercent");
		double commission = Double.parseDouble(commissionString);
		account.setCommissionPercent(commission);

		return account;
	}

	private Account getSavingsAccountFromPost(HttpServletRequest request) {

		HttpSession session = request.getSession();
		Long accountId = (Long) session.getAttribute("accountId");
		Long clientId = (Long) session.getAttribute("clientId");

		SavingsAccount account = new SavingsAccount();

		if (accountId != null)
			account.setId(accountId);
		account.setClientId(clientId);

		String valueString = request.getParameter("accountValue");
		double value = Double.parseDouble(valueString);
		account.setValue(value);

		String interestString = request.getParameter("interestPercent");
		double interest = Double.parseDouble(interestString);
		account.setInterestPercent(interest);

		return account;
	}
}