<?php
	$servername="localhost";
	$username="root";
	$pwd="";
	$db="salon";
	
	$con=mysqli_connect($servername,$username,$pwd,$db);
	if($con->connect_error)
	{
		die("connection failed:".$con->$connect_error);	
	}
	else
	{
		echo"connected successfully";
	}
    $q = "INSERT INTO customer (c_name, c_num, c_email, c_pass, c_gen, c_bdate, c_type, c_rp, c_add) VALUES ('priyank', 8,  'priyank@gmail.com', 'priyank11', 'male', '1999-21-06', 'ddd', 11, 'navsari')";
    if (mysqli_query($con, $q)) 
    {
    echo "<br>New record created successfully";
} 
else 
{
    echo "Error: " . $q . "<br>" . mysqli_error($con);
}

mysqli_close($con);
?>