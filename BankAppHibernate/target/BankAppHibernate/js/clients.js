function selectClient(clientId) {
	var form = document.getElementById("clientForm");
	var inputField = document.getElementById("clientId");
	inputField.value = clientId;
	form.submit();
}