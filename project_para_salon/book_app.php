<?php
	$response=array();
	$servername="localhost";
	$username="root";
	$password="";
	$dbname="para_salon";
	
	
	$con = mysqli_connect($servername,$username,$password,$dbname);
	
	if(!$con)
	{
		echo "connection failed:";
	}
	else
	{
		//echo"connection successful: <br>";
	}
	
if (isset($_GET['c_id']) && isset($_GET['service_id'] ) && isset($_GET['service_name'] ) && isset($_GET['emp_name'] ) && isset($_GET['app_date'] ) && isset($_GET['app_time'] ) )
{
	$email=$_GET['c_id'];
	$service_id=$_GET['service_id'];
	$service_name=$_GET['service_name'];
    $emp_name=$_GET['emp_name'];
	$date=$_GET['app_date'];
	$time=$_GET['app_time'];
    $status=$_GET['status'];
	
	//echo"done..";
	
	$sql=" INSERT INTO appointment(`c_id`, `service_id`, `service_name`, `emp_name`, `app_date`, `app_time`, `status`) VALUES( '".$email."', '".$service_id."', '".$service_name."', '".$emp_name."', '".$date."', '".$time."', '".$status."') " ;
	
	if($con->query($sql)===true)
	{
        $response["Customer Email"]=$email;
		$response["status"]=1;
		$response["message"]="insert successfully";
		//echo"new record added successfully:<br>";

        
	}
	else
	{
		$response["status"]=0;
			$response["message"]="Error";
		echo"Error:".$sql;
	}
		echo json_encode($response);

}
	else
	{
		$response["status"]=0;
			$response["message"]="Error";
		//echo"Error:".$sql;
	}

		echo json_encode($response);
?>
	