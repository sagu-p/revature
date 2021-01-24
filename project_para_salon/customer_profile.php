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
$db="salon";


/*function connect() {
        // import database connection variables
        		
		$connection = mysqli_connect($servername,$username,$password,$dbname);

        return $connection;
    }

$connection = connect();
*/
$connection = mysqli_connect($s_name,$username,$password,$db);

// check for required fields

    
    $Email= $_GET['c_email'];
   // $Email="akshit@gmail.com";

	
   // $check="SELECT * FROM unique_code WHERE name='".$Name."'";
		
	$check="SELECT * FROM customer WHERE c_email='".$Email."'";
		
    $rs=$connection->query($check);
    if ($rs->num_rows) 
	{
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             $product = array();
				$product['Email']=$row['c_email'];
				//$product['Password']=$row['password'];
				$product['Name']=$row['c_name'];
                $product['Number']=$row['c_num'];
                $product['Gender']=$row['c_gen'];
                $product['Bdate']=$row['c_bdate'];
                $product['Reward_point']=$row['c_rp'];
                $product['Address']=$row['c_add'];
                
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
            //$product['Password']=$row['password'];
			$product['Name']=$row['name'];
            $product['Number']=$row['number'];
            $product['Gender']=$row['gender'];
            $product['Bdate']=$row['bdate'];
            $product['Reward_point']=$row['reward_point'];
            $product['Address']=$row['address'];
           
            $product["message"] = "No User Match."; 
            $product["status"] = 0;
            array_push($response["Android"], $product);
            echo json_encode($response);
            
		}
             
         
    
?>