<?php

$server = "localhost";
$username = "root";
$password = "";
$dbname = "applicationdb";

$cnnx = new mysqli($server, $username, $password, $dbname);

if($cnnx->connect_error){
	die("connection failed." . $cnnx->connect_error);
}
?>