<?php

$response = array();

function connect()
{
	
	include("salon_connect.php");
	$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

	return $connection;

}

$connection=connect();
if(isset($_GET['servicecart_id']))
{
	/*$Salon_id = $_GET['salon_id'];
	$Email = $_GET['email'];*/
	$Cart_id = $_GET['servicecart_id'];

	// sql to delete a record
	//email='".$Email."'and salon_id='".$Salon_id."'and
	$sql = "DELETE FROM cart_service WHERE  servicecart_id='".$Cart_id."'";

	$rs = $connection->query($sql);
	if( $rs === TRUE)
	{
		$response["Success"] = 1;
		$response["Message"] = "Delete successfully.";
	}
	else
	{
		$response["Success"] = 0;
		$response["Message"] = "Oops! An Error occurred.";
	}
	echo json_encode($response);
}
else
{
	$response["Success"] = 0;
	$response["Message"] = "Requried field(s) is missing.";
	echo json_encode($response);
}
		