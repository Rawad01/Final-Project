<?php
include "connection.php";

$query = "SELECT * FROM reports";
$stmt = $cnnx->prepare($query);
$stmt->execute();

$results = $stmt->get_result();

$response = [];
while($report = $results->fetch_assoc()){
	$response[] = $report;
}

$reports_json = json_encode($response);
echo $reports_json; 

?>