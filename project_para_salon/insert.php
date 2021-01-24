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
	
	$name=$_GET['c_name'];
	$email=$_GET['c_email'];
	$pass=$_GET['c_pass'];
	$num=$_GET['c_num'];
	$gen=$_GET['c_gen'];
	$add=$_GET['c_add'];
    $bdate=$_GET['c_bdate'];
    $rp=$_GET['c_rp'];
    $type=$_GET['c_type'];

	
	
	$sql=" INSERT INTO customer(`c_name`, `c_num`, `c_email`, `c_pass`, `c_gen`, `c_bdate`, `c_rp`, `c_type`, `c_add`) VALUES( '".$name."', '".$num."', '".$email."', '".$pass."', '".$gen."', '".$bdate."', '".$rp."', '".$type."', '".$add."' ) " ;
	
	if($con->query($sql)===true)
	{
		$response["status"]=1;
		$response["message"]="insert successfully";
		//echo"new record added successfully:<br>";
		
	}
	else
	{
		$response["status"]=0;
			$response["message"]="Error";
		//echo"Error:".$sql;
	}
		echo json_encode($response);
?>
	