<?php
// Insert Data in url
//insertcart.php?SubProduct_Id=1&&Product_Weight=200&&Price=25&&User_Id=123456&&Quantity=10
// array for JSON response
error_reporting(0);
$response = array();


function connect() {
        // import database connection variables
        include("salon_connect.php");
        
       
        $connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

    $connection = connect();

// check for required fields
if (isset($_GET['email']) && isset($_GET['emp_id'] )&& isset($_GET['emp_name'] )&& isset($_GET['date'] )&& isset($_GET['time'] )&& isset($_GET['app_status'] )) {
    
    $Email= $_GET['email'];
	$Emp_id= $_GET['emp_id'];
    $Emp_name= $_GET['emp_name'];
    $Date= $_GET['date'];
    $Time= $_GET['time'];
    $Status= $_GET['app_status'];

	

   // $check="SELECT * FROM registration WHERE name='".$Name."'";
		
		 $check="SELECT * FROM `cart_service` WHERE email='".$Email."' && app_status='".$Status."'";
		
    $rs=$connection->query($check);
    if ($rs->num_rows) 
	{
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             $product = array();
				if(!Email == $row['email'])
				{
                $product['Email']=$row['email'];
                $product["message"] = "Enter valid email";
                $product["status"] = 2;
				}
				else
				{
    // mysql inserting a new row
				$sql="update `cart_service` set emp_id='$Emp_id',emp_name='$Emp_name',date='$Date',time='$Time' where email='$Email'  and app_status='$Status' ";
		
        if($connection->query($sql) === TRUE){
            $product["success"] = 1;
            $product["message"] = "Insert successfully."; 
        }else{
        	$product["success"] = 0;
            $product["message"] = "Oops! An Error occurred."; 
        }
    }
    echo json_encode($response);


             array_push($response["Android"], $product);
			}
         
    }
	
    // check if row inserted or not
}else{
        // required field is missing
        $response["success"] = 0;
        $response["message"] = "Required field(s) is missing";

        // echoing JSON response
        echo json_encode($response);
    }
?>





