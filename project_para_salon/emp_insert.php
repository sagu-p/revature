<?php
	$response=array();
	$servername="localhost";
	$username="root";
	$password="";
	$dbname="salon";

    
	
	
	$con = mysqli_connect($servername,$username,$password,$dbname);
	
	if(!$con)
	{
		echo "connection failed:";
	}
	else
	{
		//echo"connection successful: <br>";
	}
	
    if ( isset( $_GET['emp_name'] ) && isset( $_GET['emp_mob_num'] ) ) {
        
	$name=$_GET['emp_name'];
	$email=$_GET['emp_email'];
	$pass=$_GET['emp_pass'];
	$num=$_GET['emp_mob_num'];
	$gen=$_GET['emp_gen'];
	$add=$_GET['emp_add'];
    $bdate=$_GET['emp_bdate'];
    $qual=$_GET['emp_qualification'];
    $sal=$_GET['emp_sal'];

	
    $query=" SELECT * FROM employee WHERE emp_email='".$email."' or emp_mob_num='".$num."' ";
		
    
    $rs=$con->query($query);
    
    if ($rs->num_rows) 
	{
		//echo "add";
         $response["Android"] = array();
			while ($row = $rs->fetch_assoc()) 
			{
             
				$product = array();
                $product['wmp_email']=$row['emp_email'];    
                $product['emp_mob_num']=$row['emp_mob_num'];
                $product["message"] = "User Already in Exists."; 
                $product["status"] = 2;
                array_push($response["Android"], $product);
				
			}
         
    }


	
	//$sql=" INSERT INTO employee(`emp_name`, `emp_mob_num`, `emp_email`, `emp_pass`, `emp_gen`, `emp_bdate`, `emp_qualification`, `emp_sal`, `emp_add`) VALUES( '".$name."', '".$num."', '".$email."', '".$pass."', '".$gen."', '".$bdate."', '".$qual."', '".$sal."', '".$add."' ) " ;
	
	 else{

      $sql=" INSERT INTO employee(`emp_name`, `emp_mob_num`, `emp_email`, `emp_pass`, `emp_gen`, `emp_bdate`, `emp_qualification`, `emp_sal`, `emp_add`) VALUES( '".$name."', '".$num."', '".$email."', '".$pass."', '".$gen."', '".$bdate."', '".$qual."', '".$sal."', '".$add."' ) " ;
        if($con->query($sql)===true)
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