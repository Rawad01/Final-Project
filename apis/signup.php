<?php
include "connection.php";
 	
$response = array();
if(isset($_GET["apicall"])){
 
	switch($_GET["apicall"]){
 
		case "signup":
 
		$username = $_POST["username"]; 
		$email = $_POST["email"]; 
		$password = md5($_POST["password"]);
	
		$stmt = $cnnx->prepare("SELECT id FROM users WHERE Full_name = ? OR email = ?");
		$stmt->bind_param("ss", $username, $email);
		$stmt->execute();
		$stmt->store_result();
 
		if($stmt->num_rows > 0){
			
		$response["error"] = true;
		$response["message"] = "User is already registered";
		$stmt->close();
		
		}
		else{
			
			$stmt = $cnnx->prepare("INSERT INTO users (Full_name, email, password) VALUES (?, ?, ?)");
			$stmt->bind_param("sss", $username, $email, $password);
	
			if($stmt->execute()){

				$stmt = $cnnx->prepare("SELECT id, id, Full_name, email FROM users WHERE Full_name = ?"); 
				$stmt->bind_param("s",$username);
				$stmt->execute();
				$stmt->bind_result($userid, $id, $username, $email);
				$stmt->fetch();

				$user = array(
				'id'=>$id, 
				'username'=>$username, 
				'email'=>$email,
				);
	
				$stmt->close();	 
				$response["error"] = false; 
				$response["message"] = "user has been registered successfully"; 
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