<?php
$response = array();


function connect() 
	{
    
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();
	if (isset($_GET['email'] )) 
	{
    
    	//$Cat_id= $_GET['cat_id'];
		$Email=$_GET['email'];
		//$Salon_id = $_GET['salon_id'];

		$result=$connection->query("select * from cart_service where email='".$Email."' ");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["servicecart_id"]=$row["servicecart_id"];
				$pro["sub_cat_id"]=$row["sub_cat_id"];
				$pro["sub_cat_name"]=$row["sub_cat_name"];
				$pro["sub_cat_price"]=$row["sub_cat_price"];
				$pro["sub_cat_image"]=$row["sub_cat_image"];
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