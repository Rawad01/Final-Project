<?php
include "connection.php";
 	
$response = array();
if(isset($_GET["apicall"])){
 
	switch($_GET["apicall"]){
 
		case "medicalReport":
		
		$username = $_POST['username'];
		$full_name;
		$age; 
		$phone_num;
		$address;
		$dr_name;
		$reason_ass;
		$medicines_reff;
		$family_mh;
		$past_mh;
		$examinations_find;
		$signature;
		$date;
		 
 
		$stmt = $cnnx->prepare("SELECT * FROM reports WHERE Full_name = ?");
		$stmt->bind_param("s",$username);
		$stmt->execute();
		$stmt->store_result();
 
		if($stmt->num_rows > 0){
 
			$stmt->bind_result($id, $full_name, $age, $phone_num, $address, $dr_name, $reason_ass, $medicines_reff, $family_mh, $past_mh, $examinations_find, $signature, $date);
			$stmt->fetch();
 
			$user = array(
			'id'=>$id, 
			'username'=>$full_name,
			'age'=>$age, 
			'phone_num'=>$phone_num,
			'address'=>$address, 
			'dr_name'=>$dr_name,
			'reasons_ass'=>$reason_ass, 
			'med_reff'=>$medicines_reff,
			'family_mh'=>$family_mh, 
			'past_mh'=>$past_mh,
			'examinations_find'=>$examinations_find, 
			'signature'=>$signature,
			'date'=>$date
			);
 
			$response['error'] = false; 
			$response['message'] = 'report was successfully gotten'; 
			$response['user'] = $user;
			
		}
		else{
  
			$response['error'] = false; 
			$response['message'] = 'user not found';
			
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