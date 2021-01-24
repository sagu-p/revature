<?php
$response = array();


function connect() {
        // import database connection variables
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();
	if (isset($_GET['pro_id'])) 
	{
    
    	$Pro_id= $_GET['pro_id'];
		// $check="SELECT * FROM registration WHERE name='".$Name."'";

		$result=$connection->query("select * from product where pro_id='".$Pro_id."'");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["pro_id"]=$row["pro_id"];
				$pro["pro_name"]=$row["pro_name"];
				$pro["pro_cat"]=$row["pro_cat"];
				$pro["pro_price"]=$row["pro_price"];
				$pro["pro_description"]=$row["pro_description"];
				$pro["pro_image"]=IPProduct.$row["pro_image"];
				$pro["pro_quantity"]=$row["pro_quantity"];

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