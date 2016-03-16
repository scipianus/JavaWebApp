function init() {
	formatData();
	updateLayout();
}

function formatData() {
	var moneyFields = document.getElementsByClassName("money");
	for (var i = 0; i < moneyFields.length; ++i) {
		var field = moneyFields[i];
		var nr = field.value;
		nr = parseFloat(nr).toFixed(2);
		field.value = nr;
	}
	var percentFields = document.getElementsByClassName("percent");
	for (var i = 0; i < percentFields.length; ++i) {
		var field = percentFields[i];
		var nr = field.value;
		nr = parseFloat(nr).toFixed(1);
		field.value = nr;
	}
}

function updateLayout() {
	var valueField = document.getElementById("valueField");
	var commissionField = document.getElementById("commissionField");
	var interestField = document.getElementById("interestField");
	var submitButton = document.getElementById("submitButton");
	if (valueField != null)
		valueField.readOnly = (isDelete == "true");
	if (commissionField != null)
		commissionField.readOnly = (isDelete == "true");
	if (interestField != null)
		interestField.readOnly = (isDelete == "true");
	if (submitButton != null) {
		if (isDelete == "true")
			submitButton.value = "Delete";
		else
			submitButton.value = "Save";
	}
}
