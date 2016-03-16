function formatData() {
	var moneyFields = document.getElementsByClassName("money");
	for (var i = 0; i < moneyFields.length; ++i) {
		var field = moneyFields[i];
		var nr = field.innerHTML;
		nr = parseFloat(nr).toFixed(2);
		field.innerHTML = "$" + nr;
	}
	var percentFields = document.getElementsByClassName("percent");
	for (var i = 0; i < percentFields.length; ++i) {
		var field = percentFields[i];
		var nr = field.innerHTML;
		nr = parseFloat(nr).toFixed(1);
		field.innerHTML = nr + "%";
	}
}

function updateAccount(accountId) {
	var form = document.getElementById("accountForm");
	var fieldId = document.getElementById("accountId");
	var fieldRequestType = document.getElementById("requestType");
	fieldId.value = accountId;
	fieldRequestType.value = updateEnum;
	form.submit();
}

function deleteAccount(accountId) {
	var form = document.getElementById("accountForm");
	var fieldId = document.getElementById("accountId");
	var fieldRequestType = document.getElementById("requestType");
	fieldId.value = accountId;
	fieldRequestType.value = deleteEnum;
	form.submit();
}

function createAccount() {
	var form = document.getElementById("accountForm");
	var fieldType = document.getElementById("accountType");
	var selectInput = document.getElementById("selectType");
	var fieldRequestType = document.getElementById("requestType");
	var accountType = selectInput.options[selectInput.selectedIndex].text;
	if (accountType.toLowerCase() == "current")
		fieldType.value = currentEnum;
	else if (accountType.toLowerCase() == "savings")
		fieldType.value = savingsEnum;
	fieldRequestType.value = createEnum;
	form.submit();
}