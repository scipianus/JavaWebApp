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
import com.gentlab.bankapphibernate.domain.RequestTypes;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/accounts")
public class AccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountsServlet() {
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
		Long clientId = (Long) session.getAttribute("clientId");
		String clientFullName = ClientManager.getClientFullName(clientId);
		request.setAttribute("accounts", ClientManager.getAccounts(clientId));
		request.setAttribute("clientName", clientFullName);
		getServletContext().getRequestDispatcher("/jsp/accounts.jsp").forward(request, response);
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
		session.setAttribute("requestType", requestType);

		if (requestType != RequestTypes.CREATE) {
			Long accountId = Long.parseLong(request.getParameter("accountId"));
			session.setAttribute("accountId", accountId);

			Account account = ClientManager.getAccount(accountId);
			session.setAttribute("accountType", account.getType());
		} else {
			session.setAttribute("accountId", null);

			String accountTypeString = request.getParameter("accountType");
			AccountTypes accountType = AccountTypes.fromString(accountTypeString);
			session.setAttribute("accountType", accountType);
		}

		response.sendRedirect(getServletContext().getContextPath() + "/account_details");
	}

}
