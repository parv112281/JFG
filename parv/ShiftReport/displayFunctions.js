var actualMOPsLabel = ["Cash", "Debit Cards", "Credit Cards", "Coupons", "Payouts", "Checks"];
var actualMOPsID = ["actualCash", "actualDCards", "actualCCards", "actualCoupons", "actualPayouts", "actualChecks" ];
var receiptMOPsLabel = ["Total Merchandise", "Total Fuel", "Taxes", "Refunds"];
var receiptMOPsID = ["merch", "fuel", "taxes", "refunds"];
var safeChangeLabels = ["Tens", "Fives", "Ones", "Quarters", "Dimes", "Nickels", "Pennies"];
var safeChangeIDs = ["tens", "fives", "ones", "quarters", "dimes", "nickels", "pennies"];
var shiftNumberID = "";
var result = "";
var totalMOP = 0;
var totalRptSales = 0;
var safeStartTotal = 0;
var safeEndTotal = 0;

function landingResults() {
	shiftNumberID = document.getElementById("shiftDate").value + document.getElementById("shiftNumber").value;
	sendData(["shiftDate", "shiftNumber", "name"], "shiftInfo");
	showActualMOPs();
}

function showActualMOPs() {
	constructResults(["shiftDate", "shiftNumber", "name"], ["Shift Date", "Shift Number", "Name"]);
	document.getElementById("landing").style.display = "none";
	document.getElementById("actualMOPs").style.display = "block";
	document.getElementById("actualMOPs").innerHTML = "<form>" + buildTable(actualMOPsLabel, actualMOPsID) + "<input type='button' value='Submit' onclick='actualMOPResults();' /></form>";
}

function actualMOPResults() {
	sendData(actualMOPsID, "actualMOP");
	for(var i = 0; i < actualMOPsID.length; i++) {
		totalMOP += Number(document.getElementById(actualMOPsID[i]).value);
	}
	showReceiptMOPs();
}

function showReceiptMOPs() {
	constructResults(actualMOPsID, actualMOPsLabel);
	document.getElementById("actualMOPs").style.display = "none";
	document.getElementById("receiptMOPs").style.display = "block";
	document.getElementById("receiptMOPs").innerHTML = "<form>" + buildTable(receiptMOPsLabel, receiptMOPsID) + "<input type='button' value='Submit' onclick='receiptMOPResults();' /></form>";
}

function receiptMOPResults() {
	sendData(receiptMOPsID, "receiptMOP");
	for(var i = 0; i < receiptMOPsID.length; i++) {
		totalRptSales += Number(document.getElementById(receiptMOPsID[i]).value);
	}
	showSafeChangeStart();
}

function showSafeChangeStart() {
	constructResults(receiptMOPsID, receiptMOPsLabel);
	document.getElementById("receiptMOPs").style.display = "none";
	document.getElementById("safeChangeStart").style.display = "block";
	document.getElementById("safeChangeStart").innerHTML = "<form>" + buildTable(safeChangeLabels, safeChangeIDs) + "<input type='button' value='Submit' onclick='safeChangeStartResults();' /></form>";	
}

function safeChangeStartResults() {
	sendData(safeChangeIDs, "safeStart");
	for(var i = 0; i < safeChangeIDs.length; i++) {
		safeStartTotal += Number(document.getElementById(safeChangeIDs[i]).value);
	}
	showSafeChangeEnd();
}

function showSafeChangeEnd() {
	constructResults(safeChangeIDs, safeChangeLabels);
	document.getElementById("safeChangeStart").innerHTML = "";
	document.getElementById("safeChangeStart").style.display = "none";
	document.getElementById("safeChangeEnd").style.display = "block";
	document.getElementById("safeChangeEnd").innerHTML = "<form>" + buildTable(safeChangeLabels, safeChangeIDs) + "<input type='button' value='Submit' onclick='safeChangeEndResults();' /></form>";
}

function safeChangeEndResults() {
	sendData(safeChangeIDs, "safeEnd");
	for(var i = 0; i < safeChangeIDs.length; i++) {
		safeEndTotal += Number(document.getElementById(safeChangeIDs[i]).value);
	}
	constructResults(safeChangeIDs, safeChangeLabels);
	var node = document.getElementById("safeChangeEnd");
	
	result += "<br /><br />O/S: ";
	var osTotal = (totalMOP + safeEndTotal) - (totalRptSales + safeStartTotal);
	result += osTotal + "<br />";
	node.innerHTML = result;
	
}

function buildTable(labels, ids) {
	var table = "";
	table += "<table>";
	for(var i = 0; i < labels.length; i++) {
		table += "<tr>";
		table += "<td>" + labels[i] + "</td>";
		table += "<td><input type='text' name='" + ids[i] + "' id='" + ids[i] + "' /></td>";
		table += "</tr>";
	}
	table += "</table>";
	return table;
}

function sendData(dataInputIDs, table) {
	/* var ajaxRequest = new XMLHttpRequest();
	var url = "shiftDataProcess.php";
	var data = "table=" + table + "&shiftNumberID=" + shiftNumberID;
	for(var i = 0; i < dataInputIDs.length; i++) {
		data += "&" + dataInputIDs[i] +  "=" + document.getElementById(dataInputIDs[i]).value;
	}
	console.log (data);
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	ajaxRequest.onreadystatechange = function() {
		if(ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var return_data = ajaxRequest.responseText;
			document.getElementById("testArea").innerHTML = return_data;
		}
	}
	
	ajaxRequest.send(data); */
}

function constructResults(dataInputIDs, labels) {
	for(var i = 0; i < dataInputIDs.length; i++) {
		result += labels[i] + ": " + document.getElementById(dataInputIDs[i]).value + "<br />";
	}
}