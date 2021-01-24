<?php
error_reporting(0);
$s_name ="localhost";
$response=array();
$username ="root";
$password ="";
$db="customer";

$con = new mysqli($s_name,$username,$password,$db);
if (isset($_GET['c_email']) && isset($_GET['c_pass']))
 {

	$email=$_GET['c_email'];
	$pass=$_GET['c_pass'];

	$sql = "SELECT * FROM customer WHERE c_email='".$email."' AND c_pass='".$pass."'";
	//result set rs we execute query
	$rs=$con->query($sql);
	if ($rs->num_rows)
	{
		$response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             $product = array();
				if($email == $row['c_email'] && $pass == $row['c_pass'])
				{
					$product['c_email']=$row['c_email'];
					$product['c_pass']=$row['c_pass'];
					$product["message"] = "User Match."; 
					$product["status"] = 1;
				}
				else
				{
					//$response["Android"] = array();
					$product['c_email']=$row['c_email'];
					$product['c_pass']=$row['c_pass'];
					$product["message"]="Not match";
					$product["status"]=0;
					//array_push($response["Android"], $product);
				}
                array_push($response["Android"], $product);
                echo json_encode($response);
			}
		
	}
	//echo json_encode($response);
}
// check if row inserted or not
else{
			// required field is missing
		$response["success"] = 0;
		$response["message"] = "Required field(s) is missing";
		// echoing JSON response
		echo json_encode($response);
}
	
?>