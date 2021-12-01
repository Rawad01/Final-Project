<?php
include("connection.php");

if(isset($_POST["name"]) && $_POST["name"] != ""){
	$name = $_POST["name"];
}else{
	die("Don't try to mess around bro ;)");
}

if(isset($_POST["email"]) && $_POST["email"] != ""){
	$email = $_POST["email"];
}else{
	die("Don't try to mess around bro ;)");
}


if(isset($_POST["password"]) && $_POST["password"] != ""){
	$password = hash("sha256", $_POST["password"]);
}else{
	die("Don't try to mess around bro ;)");
}

for($i = 0; $i< 100000000000000000; $i++){
	$email = $email . $i; 
	$mysql = $connection->prepare("INSERT INTO users(Full_name, email, password) VALUES (?,?,?)");
	$mysql->bind_param("sss", $name, $email, $password);
	$mysql->execute();
}



$mysql->close();
$connection->close();
header("Location:index.php");



?>