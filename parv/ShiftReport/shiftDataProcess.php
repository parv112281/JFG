<?php
$mysqli = new mysqli("127.0.0.1", "parv112281", "triumvirate", "JFG");

$table = $_POST['table'];

$shift_data = array_slice($_POST, 1);



if(mysqli_connect_errno()) {
	echo mysqli_error($mysqli);

	exit();

} else {

	$sql_pt_1 = "INSERT INTO " . $table . " (";

	$sql_pt_2 = ") VALUES (";

	$sql_pt_3 = ");";

	

	$iteration = 0;

	foreach($shift_data as $key => $value) {

		if($iteration != 0) {

			$sql_pt_1 .= ", ";

			$sql_pt_2 .= ", ";

		}

		$sql_pt_1 .= $key;

		$sql_pt_2 .= "'" . $value . "'";

		$iteration++;

	}

	

	$sql_stmt = $sql_pt_1 . $sql_pt_2 . $sql_pt_3;

	echo $sql_stmt;

	$res = mysqli_query($mysqli, $sql_stmt);

	

	if($res == TRUE) {

		echo "Success!";

	} else {

		echo mysqli_error($mysqli);

	}

	

	mysqli_close($mysqli);

}



?>