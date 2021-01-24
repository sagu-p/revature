<?php
$response = array();


function connect() 
	{
        // import database connection variables
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();

    $app_id=$_GET['app_id'];
	$c_id=$_GET['c_id'];
	$emp_name=$_GET['emp_name'];
	$service_id=$_GET['service_id'];
	$service_name=$_GET['service_name'];
	$date=$_GET['app_date'];
	$time=$_GET['app_time'];
	$status=$_GET['status'];
	
	
	
	$sql=" UPDATE appointment set `c_id`='".$c_id."', `service_id`='".$service_id."', `service_name`='".$service_name."', `emp_name`='".$emp_name."', `app_date`='".$date."', `app_time`='".$time."', `status`='".$status."' WHERE app_id='".$app_id."' " ;
	
	if($connection->query($sql)===true)
	{
        $response["Appointment_id"]=$app_id;
		$response["status"]=1;
		$response["message"]="update successfully";
		//echo"new record added successfully:<br>";
		
	}
	else
	{
		$response["status"]=0;
			$response["message"]="Error";
		//echo"Error:".$sql;
	}
		echo json_encode($response);


?>
	