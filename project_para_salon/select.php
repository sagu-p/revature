<?php
/*$db = "salon";
$response=array();
$servername = "localhost";
$usrname = "root";
$password = "";



$conn = mysqli_connect($servername,$usrname,$password, $db);

if($conn->connect_error)
{
    die("Connection failed: " . $conn->connect_error);
}
else
{
    //echo "connected";
}*/

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
if ( isset( $_GET['c_name'] ) && isset( $_GET['c_num'] ) ) {
    
$name=$_GET['c_name'];
$email=$_GET['c_email'];
$num=$_GET['c_num'];
$pass=$_GET['c_pass'];
$bdate=$_GET['c_bdate'];
$add=$_GET['c_add'];
$gen=$_GET['c_gen'];
$rp=$_GET['c_rp'];
$type=$_GET['c_type'];

   // $check="SELECT * FROM registration WHERE name='".$Name."'";
		
		 $query=" SELECT * FROM customer WHERE c_email='".$email."'
		or c_num='".$num."' ";
		
    
    $rs=$connection->query($query);
    
    if ($rs->num_rows) 
	{
		//echo "add";
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             
				$product = array();
                $product['c_email']=$row['c_email'];    
                $product['c_num']=$row['c_num'];
                $product["message"] = "User Already in Exists."; 
                $product["status"] = 2;
                array_push($response["Android"], $product);
				
			}
         
    }
    else{

       $sql=" INSERT INTO customer(`c_name`, `c_num`, `c_email`, `c_pass`, `c_gen`, `c_bdate`, `c_rp`, `c_type`, `c_add`) VALUES( '".$name."', '".$num."', '".$email."', '".$pass."', '".$gen."', '".$bdate."', '".$rp."', '".$type."', '".$add."' ) " ;
        if($connection->query($sql)===true)
        {
		  $response['status']=1;
		  $response['message']="insert successfully";
		  //echo "inserted successfully";	
        }
        else
        {
            $response['status']=0;
            $response['message']="error";
        }
    }
    echo json_encode($response);
}
?>