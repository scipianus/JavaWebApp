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
		int clientID = (int) session.getAttribute("clientID");
		String clientFullName = ClientList.getClientFullName(clientID);
		request.setAttribute("accounts", ClientList.getAccounts(clientID));
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
		AccountRequestType requestType = AccountRequestType.fromString(requestTypeString);
		session.setAttribute("requestType", requestType);

		if (requestType != AccountRequestType.CREATE) {
			int accountID = Integer.parseInt(request.getParameter("accountID"));
			session.setAttribute("accountID", accountID);

			int clientID = (int) session.getAttribute("clientID");
			BankAccount account = ClientList.getAccount(clientID, accountID);
			session.setAttribute("accountType", account.getType());
		} else {
			String accountTypeString = request.getParameter("accountType");
			BankAccountType accountType = BankAccountType.fromString(accountTypeString);
			session.setAttribute("accountType", accountType);
		}
		response.sendRedirect(getServletContext().getContextPath() + "/account_details");
	}

}
