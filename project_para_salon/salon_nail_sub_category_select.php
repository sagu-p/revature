<?php
$response = array();


function connect() {
        // import database connection variables
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }
    /*isset($_GET['cat_id']) &&
    cat_id='".$Cat_id."' or */ 

	$connection = connect();
	if (isset($_GET['cat_id']) && ($_GET['gender'] )) 
	{
    
    	$Cat_id= $_GET['cat_id'];
    	$Gender=$_GET['gender'];
		// $check="SELECT * FROM registration WHERE name='".$Name."'";

		$result=$connection->query("select * from sub_nail_catagory where cat_id='".$Cat_id."' and gender='".$Gender."'");

		if($result->num_rows)
		{
			$res["Android"]=array();
			while($row=$result->fetch_assoc())
			{
				$pro=array();
				$pro["sub_cat_id"]=$row["sub_cat_id"];
				$pro["sub_cat_name"]=$row["sub_cat_name"];
				$pro["sub_cat_price"]=$row["sub_cat_price"];
				$pro["sub_cat_image"]=IPSubCat.$row["sub_cat_image"];
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