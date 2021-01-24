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
	
    if (isset($_GET['app_id'])) 
	{
		$email=$_GET['app_id'];
        
		$result=$connection->query("select * from appointment where app_id='".$email."' ");
        
//        "select * from appointment where c_id='".$email."' ORDER BY app_id ASC"   	status

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["app_id"]=$row["app_id"];
				$pro["c_id"]=$row["c_id"];
				$pro["service_id"]=$row["service_id"];
                $pro["service_name"]=$row["service_name"];
                $pro["emp_name"]=$row["emp_name"];
                $pro["app_date"]=$row["app_date"];
                $pro["app_time"]=$row["app_time"];
                $pro["app_status"]=$row["status"];
                $pro["status"]=1;
			    $pro["message"]="found";

				array_push($res["Android"], $pro);
			}
			$res["status"]=1;
			$res["message"]="found";

			echo json_encode($res, JSON_UNESCAPED_UNICODE);
		}
		else
		{
			$pro["status"]=0;
			$pro["message"]="not found";

			echo json_encode($res);
		}
        
        array_push($res["Android"], $pro);
    }

?> 