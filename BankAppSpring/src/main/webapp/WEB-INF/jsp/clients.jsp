<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Clients</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/styles.css">
</head>

<body>
	<h1>Clients</h1>
	<table>
		<tr>
			<th>Id</th>
			<th>First name</th>
			<th>Last name</th>
			<th></th>
		</tr>

		<c:forEach var="entry" items="${clientList}">
			<tr>
				<td><c:out value="${entry.getId()}" /></td>
				<td><c:out value="${entry.getFirstname()}" /></td>
				<td><c:out value="${entry.getLastname()}" /></td>
				<td><button onclick="selectClient('${entry.getId()}')">Select</button></td>
			</tr>
		</c:forEach>

	</table>

	<form action="${pageContext.request.contextPath}/app/clients"
		id="clientForm" method="post">
		<input type="hidden" name="clientId" id="clientId">
	</form>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/clients.js"></script>
</body>

</html>