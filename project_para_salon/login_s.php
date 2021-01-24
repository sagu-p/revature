<?php
//NOT RECOMMANDED
// Insert Data in url
//insertcart.php?SubProduct_Id=1&&Product_Weight=200&&Price=25&&User_Id=123456&&Quantity=10
// array for JSON response
error_reporting(0);
/*$response = array();
$s_name ="localhost";
//$response=array();
$username ="root";
$password ="";
$db="salon";

*/

$response = array();


function connect() 
	{
        // import database connection variables
        include("salon_connect.php");
		
       
		$connection = mysqli_connect(DB_server,DB_user,DB_pass,DB_data);

        return $connection;
    }

	$connection = connect();


// check for required fields
if (isset($_GET['c_email']) && isset($_GET['c_pass'] ))
	{
    
    $Email= $_GET['c_email'];
	$Password=$_GET['c_pass'];
	}
   // $check="SELECT * FROM unique_code WHERE name='".$Name."'";

   //echo "aaa";
   //echo $Email;

   //echo $Password;
		
	$check="SELECT * FROM customer WHERE c_email='".$Email."' AND c_pass='".$Password."'";
		
    $rs=$connection->query($check);
    if ($rs->num_rows) 
	{
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             $product = array();
				$product['Email']=$row['c_email'];
				$product['Password']=$row['c_pass'];
				//$product['reg_id']=$row['reg_id'];
                $product["message"] = "User Match."; 
                $product["status"] = 1;
                array_push($response["Android"], $product);
			}
				echo json_encode($response);
			
			//array_push($response["Android"], $product);
	}
	else{
            $response["Android"] = array();
			$product['Email']=$row['Email'];
            $product['Password']=$row['password'];
			$product["message"] = "No User Match."; 
            $product["status"] = 0;
            array_push($response["Android"], $product);
            echo json_encode($response);
            
		}


             
         
    
?>