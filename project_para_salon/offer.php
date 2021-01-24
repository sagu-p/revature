<?php
	$response=array();
	$servername="localhost";
	$username="root";
	$password="";
	$dbname="salon";

    $path="http://localhost/project/upload/"
	
	
	$con = mysqli_connect($servername,$username,$password,$dbname);
	
	if(!$con)
	{
		echo "connection failed:";
	}
	else
	{
		//echo"connection successful: <br>";
	}
	
    
        
	$service_name=$_GET['service_name'];
	$img=$_GET['image'];
	$pdis=$_GET['offer_discription'];
	
	//$sql=" INSERT INTO employee(`emp_name`, `emp_mob_num`, `emp_email`, `emp_pass`, `emp_gen`, `emp_bdate`, `emp_qualification`, `emp_sal`, `emp_add`) VALUES( '".$name."', '".$num."', '".$email."', '".$pass."', '".$gen."', '".$bdate."', '".$qual."', '".$sal."', '".$add."' ) " ;
	
	 
      $sql=" INSERT INTO offer(`service_name`, `image`, `offer_discription`) VALUES( '".$service_name."', '".$img."', '".$pdis."') " ;

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
    echo json_encode($response);

?>