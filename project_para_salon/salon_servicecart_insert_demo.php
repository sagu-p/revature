<?php

$response = array();

function connect()
{
	
	include("salon_connect.php");
	$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

	return $connection;

}
$connection=connect();

if(isset($_GET['email'])&&isset($_GET['sub_cat_id'])&&isset($_GET['sub_cat_name'])&&isset($_GET['sub_cat_price'])&&isset($_GET['sub_cat_image']))
{
	$Email = $_GET['email'];
	$Sub_cat_id = $_GET['sub_cat_id'];
	$Sub_cat_name = $_GET['sub_cat_name'];
	$Sub_cat_price = $_GET['sub_cat_price'];
	$Sub_cat_image = $_GET['sub_cat_image'];
	

	$check="INSERT INTO `cart_service`( `email`,`sub_cat_id`,`sub_cat_name`,`sub_cat_price`,`sub_cat_image`) VALUES ('$Email','$Sub_cat_id','$Sub_cat_name','$Sub_cat_price','$Sub_cat_image')";
	$rs=$connection->query($check);

		if( $rs === TRUE)
		{
			$response["Success"] = 1;
			$response["Message"] = "Insert successfully.";
			echo json_encode($response);
		}
		else
		{
			$response["Success"] = 0;
			$response["Message"] = "Oops! An Error occurred.";
		}


}
else
{
	$response["Success"] = 0;
	$response["Message"] = "Requried field(s) is missing.";
	echo json_encode($response);
}
?>
