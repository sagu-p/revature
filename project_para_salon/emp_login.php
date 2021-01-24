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
if (isset($_GET['emp_email']) && isset($_GET['emp_pass'] ))
	{
    
    $Email= $_GET['emp_email'];
	$Password=$_GET['emp_pass'];
	}
   // $check="SELECT * FROM unique_code WHERE name='".$Name."'";
		
	$check="SELECT * FROM employee WHERE emp_email='".$Email."'AND emp_pass='".$Password."'";
		
    $rs=$connection->query($check);
    if ($rs->num_rows) 
	{
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             $product = array();
				$product['Email']=$row['Email'];
				$product['Password']=$row['password'];
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