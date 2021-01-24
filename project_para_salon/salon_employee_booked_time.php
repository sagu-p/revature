<?php
$response = array();


function connect() 
	{
    
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();
	if (isset($_GET['emp_id'] )&&isset($_GET['date'])) 
	{
    
    	//$Cat_id= $_GET['cat_id'];
		$Emp_id=$_GET['emp_id'];
		$Date= $_GET['date'];

		$result=$connection->query("select * from cart_service where emp_id='".$Emp_id."'and date='".$Date."' and app_status=0");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["time"]=$row["time"];
				/*$pro["sub_cat_id"]=$row["sub_cat_id"];
				$pro["sub_cat_name"]=$row["sub_cat_name"];
				$pro["sub_cat_price"]=$row["sub_cat_price"];
				$pro["sub_cat_image"]=$row["sub_cat_image"];*/
				//$pro["sub_cat_image"]=$row["sub_cat_image"];
				
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
	}
?>