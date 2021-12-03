<?php
include "connection.php";
 	
$response = array();
if(isset($_GET["apicall"])){
 
	switch($_GET["apicall"]){
 
		case "signin":
		
		$username = $_POST['username'];
		$password = md5($_POST['password']); 
 
		$stmt = $cnnx->prepare("SELECT id, Full_name, email FROM users WHERE Full_name = ? AND password = ?");
		$stmt->bind_param("ss",$username, $password);
		$stmt->execute();
		$stmt->store_result();
 
		if($stmt->num_rows > 0){
 
			$stmt->bind_result($id, $username, $email);
			$stmt->fetch();
 
			$user = array(
			'id'=>$id, 
			'username'=>$username, 
			'email'=>$email,
			);
 
			$response['error'] = false; 
			$response['message'] = 'Login successfull'; 
			$response['user'] = $user;
			
		}
		else{
  
			$response['error'] = false; 
			$response['message'] = 'Invalid username or password';
			
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