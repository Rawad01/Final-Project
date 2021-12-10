<?php
include "connection.php";
 	
$response = array();
if(isset($_GET["apicall"])){
 
	switch($_GET["apicall"]){
 
		case "addreport":
 
		$username = $_POST["username"]; 
		$age = $_POST["age"]; 
		$phone_num = $_POST["phone_number"];
		$address = $_POST["address"];
		$dr_name = $_POST["doctor_name"];
		$reason_ass = $_POST["reasons_for_assessment"];
		$medicines_reff = $_POST["medicines_reffered"];
		$family_mh = $_POST["family_medical_history"];
		$past_mh = $_POST["past_medical_history"];
		$examinations_find = $_POST["examinations_findings"];
		$signature = md5($_POST["signature"]);
		$date = $_POST["date"];
	
		$stmt = $cnnx->prepare("SELECT id FROM reports WHERE full_name = ?");
		$stmt->bind_param("s", $username);
		$stmt->execute();
		$stmt->store_result();
 
		if($stmt->num_rows > 0){
			
		$response["error"] = true;
		$response["message"] = "User already has a medical report";
		$stmt->close();
		
		}
		else{
			
			$stmt = $cnnx->prepare("INSERT INTO reports (full_name, age, phone_number, address, doctor_name, reasons_for_assessment, medicines_reffered, family_medical_history, past_medical_history, examinations_findings, signature, date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			$stmt->bind_param("ssssssssssss", $username, $age, $phone_num, $address, $dr_name, $reason_ass, $medicines_reff, $family_mh, $past_mh, $examinations_find, $signature, $date);
	
			if($stmt->execute()){

				$stmt = $cnnx->prepare("SELECT id, id, full_name, date FROM reports WHERE full_name = ?"); 
				$stmt->bind_param("s",$username);
				$stmt->execute();
				$stmt->bind_result($userid, $id, $username, $date);
				$stmt->fetch();

				$user = array(
				'id'=>$id, 
				'username'=>$username, 
				'date'=>$date,
				);
	
				$stmt->close();	 
				$response["error"] = false; 
				$response["message"] = "user report has been added successfully"; 
				$response["user"] = $user; 
			}
		}
		break; 
  
		default: 
	
		$response["error"] = true; 
		$response["message"] = "incorrect operation";
	
	}
 
}
else{
	
	$response["error"] = true; 
	$response["message"] = "api not found";
	
}

echo json_encode($response);

?>