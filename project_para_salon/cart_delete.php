<?php
//NOT RECOMMANDED
// Insert Data in url
//insertcart.php?SubProduct_Id=1&&Product_Weight=200&&Price=25&&User_Id=123456&&Quantity=10
// array for JSON response
error_reporting(0);
$response = array();
$s_name ="localhost";
//$response=array();
$username ="root";
$password ="";
$db="para_salon";


/*function connect() {
        // import database connection variables
        		
		$connection = mysqli_connect($servername,$username,$password,$dbname);

        return $connection;
    }

$connection = connect();
*/
$connection = mysqli_connect($s_name,$username,$password,$db);

// check for required fields

if (isset($_GET['email'])) {
    
    $p_id= $_GET['email'];
   // $Email="akshit@gmail.com";

	
   // $check="SELECT * FROM unique_code WHERE name='".$Name."'";
		
	$check="DELETE FROM cart_service WHERE email='".$p_id."'";
		
    //$rs=$connection->query($check);
    if ($connection->query($check)===true) 
	{
             $response["message"] = "DONE..."; 
             $response["status"] = 1;
	//array_push($response["Android"], $product);
	}
	else{      
            $response["message"] = "No cart Avalable."; 
            $response["status"] = 0;
                
	}     
         
}
else{      
        $response["message"] = "Select the cart."; 
        $response["status"] = 10;        
	}
    echo json_encode($response);
?>