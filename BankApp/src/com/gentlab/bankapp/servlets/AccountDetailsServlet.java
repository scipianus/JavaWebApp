package com.gentlab.bankapp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gentlab.bankapp.models.AccountRequestType;
import com.gentlab.bankapp.models.BankAccount;
import com.gentlab.bankapp.models.BankAccountType;
import com.gentlab.bankapp.models.ClientList;
import com.gentlab.bankapp.models.CurrentAccount;
import com.gentlab.bankapp.models.SavingsAccount;

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
		AccountRequestType requestType = (AccountRequestType) session.getAttribute("requestType");
		int clientID, accountID;
		BankAccountType accountType = (BankAccountType) session.getAttribute("accountType");
		if (requestType == AccountRequestType.CREATE) {
			request.setAttribute("accountID", ClientList.getNewId());
		} else {
			clientID = (int) session.getAttribute("clientID");
			accountID = (int) session.getAttribute("accountID");
			request.setAttribute("account", ClientList.getAccount(clientID, accountID));
			request.setAttribute("accountID", accountID);
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
		AccountRequestType requestType = AccountRequestType.fromString(requestTypeString);

		BankAccount account = getAccountFromPost(request);
		int clientID = (int) session.getAttribute("clientID");

		if (requestType == AccountRequestType.CREATE)
			ClientList.addAccount(clientID, account);
		else if (requestType == AccountRequestType.UPDATE)
			ClientList.updateAccount(clientID, account);
		else if (requestType == AccountRequestType.DELETE)
			ClientList.removeAccount(clientID, account);

		response.sendRedirect(getServletContext().getContextPath() + "/accounts");
	}

	private BankAccount getAccountFromPost(HttpServletRequest request) {
		String accountTypeString = request.getParameter("accountType");
		BankAccountType accountType = BankAccountType.fromString(accountTypeString);

		BankAccount account = null;

		if (accountType == BankAccountType.CURRENT_ACCOUNT) {
			account = getCurrentAccountFromPost(request);
		} else if (accountType == BankAccountType.SAVINGS_ACCOUNT) {
			account = getSavingsAccountFromPost(request);
		}
		return account;
	}

	private BankAccount getCurrentAccountFromPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int accountID = (int) session.getAttribute("accountID");

		String valueString = request.getParameter("accountValue");
		double value = Double.parseDouble(valueString);

		String commissionString = request.getParameter("commissionPercent");
		double commission = Double.parseDouble(commissionString);

		CurrentAccount account = new CurrentAccount(value, commission, accountID);
		return account;
	}

	private BankAccount getSavingsAccountFromPost(HttpServletRequest request) {
		HttpSession session = request.getSession();
		int accountID = (int) session.getAttribute("accountID");

		String valueString = request.getParameter("accountValue");
		double value = Double.parseDouble(valueString);

		String interestString = request.getParameter("interestPercent");
		double interest = Double.parseDouble(interestString);

		SavingsAccount account = new SavingsAccount(value, interest, accountID);
		return account;
	}
}
