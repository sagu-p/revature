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
	if ($_GET['gender'] ) 
	{
    
		$Gender=$_GET['gender'];
		$result=$connection->query("select * from skin_catagory where gender='".$Gender."'");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["main_cat_id"]=$row["main_cat_id"];
				$pro["main_cat_name"]=$row["main_cat_name"];
				$pro["main_cat_image"]=IPMainCat.$row["main_cat_image"];
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