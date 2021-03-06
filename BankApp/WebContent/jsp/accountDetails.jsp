<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.gentlab.bankapp.models.BankAccountType"%>
<%@page import="com.gentlab.bankapp.models.AccountRequestType"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account details</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/styles.css">
<script type="text/javascript">
	var isUpdate = '${requestType == AccountRequestType.UPDATE}';
	var isDelete = '${requestType == AccountRequestType.DELETE}';
	var isCreate = '${requestType == AccountRequestType.CREATE}';
</script>
</head>

<body onload="init()">
	<h1>
		Details for the account
		<c:out value="${accountID}" />
	</h1>

	<form action="${pageContext.request.contextPath}/account_details"
		method="post" id="detailsForm">
		<fieldset>
			<input type="hidden" name="accountType" value="${accountType}">
			<input type="hidden" name="accountID" value="${accountID}">
			Type:
			<c:if test="${accountType == BankAccountType.CURRENT_ACCOUNT}">
				<input type="radio" name="accountTypeField" value="current_account"
					disabled checked>Current account
				<input type="radio" name="accountTypeField" value="savings_account"
					disabled>Savings account
			</c:if>
			<c:if test="${accountType == BankAccountType.SAVINGS_ACCOUNT}">
				<input type="radio" name="accountTypeField" value="current_account"
					disabled>Current account
				<input type="radio" name="accountTypeField" value="savings_accounts"
					disabled checked>Savings account
			</c:if>
			<br> Value: <input type="number" id="valueField"
				name="accountValue" class="money" min="0" max="100000000" step="0.01"
				value="${account.getValue()}" required>
			<c:if test="${accountType == BankAccountType.CURRENT_ACCOUNT}">
				<br> Commission percent: <input type="number"
					name="commissionPercent" id="commissionField" class="percent"
					min="0" max="100" step="0.1" value="${account.getCommissionPercent()}"
					required>
			</c:if>
			<c:if test="${accountType == BankAccountType.SAVINGS_ACCOUNT}">
				<br> Interest percent: <input type="number"
					name="interestPercent" id="interestField" class="percent" min="0"
					max="100" step="0.1" value="${account.getInterestPercent()}" required>
			</c:if>
			<input type="hidden" name="requestType" value="${requestType}">
		</fieldset>
		<br>
		<input type="submit" id="submitButton" value="Save">
	</form>

	<a href="${pageContext.request.contextPath}/accounts"><button>Back</button></a>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/accountDetails.js"></script>
</body>
</html>