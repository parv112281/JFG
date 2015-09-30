function submitReadings() {
	sendData(["regunl", "midunl", "preunl", "diesel"], "gasReadings");
}

function sendData(dataInputIDs, table) {
	var ajaxRequest = new XMLHttpRequest();
	var url = "gasReading.php";
	var data = "table=" + table;
	for(var i = 0; i < dataInputIDs.length; i++) {
		data += "&" + dataInputIDs[i] +  "=" + document.getElementById(dataInputIDs[i]).value;
	}
	console.log (data);
	ajaxRequest.open("POST", url, true);
	ajaxRequest.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	
	ajaxRequest.onreadystatechange = function() {
		if(ajaxRequest.readyState == 4 && ajaxRequest.status == 200) {
			var return_data = ajaxRequest.responseText;
			console.log (return_data);
		}
	}
	
	ajaxRequest.send(data);
}
