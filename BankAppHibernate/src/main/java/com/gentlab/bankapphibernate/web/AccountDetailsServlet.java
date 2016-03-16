package com.gentlab.bankapphibernate.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gentlab.bankapphibernate.ClientManager;
import com.gentlab.bankapphibernate.domain.Account;
import com.gentlab.bankapphibernate.domain.AccountTypes;
import com.gentlab.bankapphibernate.domain.CurrentAccount;
import com.gentlab.bankapphibernate.domain.RequestTypes;
import com.gentlab.bankapphibernate.domain.SavingsAccount;

/**
 * Servlet implementation class AccountDetailsServlet
 */
@WebServlet("/account_details")

public class AccountDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountDetailsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		RequestTypes requestType = (RequestTypes) session.getAttribute("requestType");
		Long accountId;
		AccountTypes accountType = (AccountTypes) session.getAttribute("accountType");
		if (requestType == RequestTypes.CREATE) {

		} else {
			accountId = (Long) session.getAttribute("accountId");
			request.setAttribute("account", ClientManager.getAccount(accountId));
		}
		request.setAttribute("accountType", accountType);
		request.setAttribute("requestType", requestType);
		getServletContext().getRequestDispatcher("/jsp/accountDetails.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String requestTypeString = request.getParameter("requestType");
		RequestTypes requestType = RequestTypes.fromString(requestTypeString);

		Account account = getAccountFromPost(request);

		if (requestType == RequestTypes.CREATE || requestType == RequestTypes.UPDATE)
			ClientManager.saveAccount(account);
		else if (requestType == RequestTypes.DELETE)
			ClientManager.removeAccount(account);

		response.sendRedirect(getServletContext().getContextPath() + "/accounts");

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
