<?php
	//require 'shiftDataProcess.php';

	$regunl = (int) $_POST['regunl'];
	$midunl = (int) $_POST['midunl'];
	$preunl = (int) $_POST['preunl'];
	$diesel = (int) $_POST['diesel'];
	$totLoad = 0;

	$message = "Hi, I'd like to place a load order for tomorrow, ";
	
	if($diesel <= 700){
		$message .= "1000 diesel, ";
		$totLoad += 1000;
	}
	if($preunl <= 800){
		$message .= "1000 premium, ";
		$totLoad += 1000;
	}
	if($midunl <= 700){
		$message .= "1000 midgrade, ";
		$totLoad += 1000;
	}
	if($regunl <= 3000){
		$message .= (8500 - $totLoad) . " regular for Jassy Gas and Food ";
		$message .= "in Madison, IN.\n";
		$totLoad = 8500;
	}


	if($totLoad == 8500) {
		$to = "psingh6@wgu.edu";
		$subject = "Load Order";
		$headers = 'From: parv.jdt@gmail.com' . "\r\n" .
    	'Reply-To: parv112281@hotmail' . "\r\n" .
    	'X-Mailer: PHP/' . phpversion();
		
		echo $message . "\n";
		mail($to, $subject, $message, $headers);
		mail("parv112281@gmail.com", $subject, $message, $headers);
		
	}
?>
