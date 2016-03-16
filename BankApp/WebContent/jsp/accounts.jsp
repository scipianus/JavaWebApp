<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.gentlab.bankapp.models.BankAccountType"%>
<%@page import="com.gentlab.bankapp.models.AccountRequestType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Accounts</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">
	
	<script type="text/javascript">
		var updateEnum = '${AccountRequestType.UPDATE}';
		var deleteEnum = '${AccountRequestType.DELETE}';
		var createEnum = '${AccountRequestType.CREATE}';
		var currentEnum = '${BankAccountType.CURRENT_ACCOUNT}';
		var savingsEnum = '${BankAccountType.SAVINGS_ACCOUNT}';
	</script>
	
</head>



<body onload="formatData()">
	<h1>
		Accounts for
		<c:out value="${clientName}" />
	</h1>
	<table>
		<tr>
			<th>ID</th>
			<th>Type</th>
			<th>Value</th>
			<th>Interest</th>
			<th>Commission</th>
			<th></th>
		</tr>

		<c:forEach var="entry" items="${accounts}">
			<tr>
				<td><c:out value="${entry.value.getID()}" /></td>
				<c:if test="${entry.value.isCurrentAccount() == false}">
					<td>Savings</td>
					<td class="money"><c:out value="${entry.value.getValue()}" /></td>
					<td class="percent"><c:out
							value="${entry.value.getInterestPercent()}" />
					<td></td>
				</c:if>
				<c:if test="${entry.value.isCurrentAccount() == true}">
					<td>Current</td>
					<td class="money"><c:out value="${entry.value.getValue()}" /></td>
					<td></td>
					<td class="percent"><c:out
							value="${entry.value.getCommissionPercent()}" />
				</c:if>
				<td>
					<button onclick="updateAccount('${entry.value.getID()}')">Change</button>
					<button onclick="deleteAccount('${entry.value.getID()}')">Delete</button>
				</td>
			</tr>
		</c:forEach>

	</table>

	<select id="selectType">
		<option value="current">Current</option>
		<option value="savings">Savings</option>
	</select>

	<button onclick="createAccount()">Create new account</button>

	<form action="${pageContext.request.contextPath}/accounts" id="accountForm" method="post">
		<input type="hidden" name="accountID" id="accountID">
		<input type="hidden" name="accountType" id="accountType">
		<input type="hidden" name="requestType" id="requestType">
	</form>
	
	<a href="${pageContext.request.contextPath}/clients"><button>Back</button></a>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/accounts.js"></script>
</body>
</html>