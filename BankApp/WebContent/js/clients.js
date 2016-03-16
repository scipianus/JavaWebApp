function selectClient(clientID) {
	var form = document.getElementById("clientForm");
	var inputField = document.getElementById("clientID");
	inputField.value = clientID;
	form.submit();
}