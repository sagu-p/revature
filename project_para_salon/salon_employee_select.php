<?php
$response = array();


function connect() {
        // import database connection variables
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();
	//if (isset($_GET['salon_id'])) 
	//{
    
    //	$Salon_id= $_GET['salon_id'];
		
		// $check="SELECT * FROM registration WHERE name='".$Name."'";

		$result=$connection->query("select * from employee ");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["emp_id"]=$row["emp_id"];
				$pro["emp_name"]=$row["emp_name"];
				$pro["emp_rate"]=$row["emp_rate"];
				/*$pro["emp_email"]=$row["emp_email"];
				$pro["emp_mobile_no"]=$row["emp_mobile_no"];*/
				$pro["emp_image"]=IPEmployee.$row["emp_image"];
				
				array_push($res["Android"], $pro);
			}
			$res["status"]=1;
			$res["message"]="found";

			echo json_encode($res, JSON_UNESCAPED_UNICODE);
		}
		else
		{
			$res["status"]=0;
			$res["message"]="not found";

			echo json_encode($res);
		}
	//}
?>