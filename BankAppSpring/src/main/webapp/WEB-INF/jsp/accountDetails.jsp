<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.gentlab.bankappspring.model.AccountTypes"%>
<%@page import="com.gentlab.bankappspring.model.RequestTypes"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account details</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
<script type="text/javascript">
	var isUpdate = '${requestType == RequestTypes.UPDATE}';
	var isDelete = '${requestType == RequestTypes.DELETE}';
	var isCreate = '${requestType == RequestTypes.CREATE}';
</script>
</head>

<body onload="init()">
	<h1>
		Details for the account
		<c:out value="${account.getId()}" />
	</h1>

	<form action="${pageContext.request.contextPath}/app/account_details"
		method="post" id="detailsForm">
		<fieldset>
			<input type="hidden" name="accountType" value="${accountType}">
			<input type="hidden" name="accountId" value="${account.getId()}">
			Type:
			<c:if test="${accountType == AccountTypes.CURRENT}">
				<input type="radio" name="accountTypeField"
					value="${AccountTypes.CURRENT}" disabled checked>Current account
				<input type="radio" name="accountTypeField"
					value="${AccountTypes.SAVINGS}" disabled>Savings account
			</c:if>
			<c:if test="${accountType == AccountTypes.SAVINGS}">
				<input type="radio" name="accountTypeField"
					value="${AccountTypes.CURRENT}" disabled>Current account
				<input type="radio" name="accountTypeField"
					value="${AccountTypes.SAVINGS}" disabled checked>Savings account
			</c:if>
			<br> Value: <input type="number" id="valueField"
				name="accountValue" class="money" min="0" max="100000000"
				step="0.01" value="${account.getValue()}" required>
			<c:if test="${accountType == AccountTypes.CURRENT}">
				<br> Commission percent: <input type="number"
					name="commissionPercent" id="commissionField" class="percent"
					min="0" max="100" step="0.1"
					value="${account.getCommissionPercent()}" required>
			</c:if>
			<c:if test="${accountType == AccountTypes.SAVINGS}">
				<br> Interest percent: <input type="number"
					name="interestPercent" id="interestField" class="percent" min="0"
					max="100" step="0.1" value="${account.getInterestPercent()}"
					required>
			</c:if>
			<input type="hidden" name="requestType" value="${requestType}">
		</fieldset>
		<br> <input type="submit" id="submitButton" value="Save">
	</form>

	<a href="${pageContext.request.contextPath}/app/accounts"><button>Back</button></a>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/accountDetails.js"></script>
</body>
</html>