<?php
require "connectWithDB.php";

$username=$_POST['username'];
$password=$_POST['password'];

$role=$_POST['role'];


$sql="SELECT * FROM users WHERE username='$username' OR username='$usermail' AND 'password'=$password'";
$result=my_sqli_query($con,$sql);
$response = array();
//$check=my_sqli_fetch_array($result);
if(mysqli_num_rows($result)>0){
	$row=mysqli_fetch_row($result);
	$name=$row[1];
	$email=$row[5];
	$code = "login_success";
	array_push($response , array("code"->$code,"message"->$message));
	echo json_encode($response);
}else{
$code="login fail";
$message = "User not found . Try again";
array_push($response , array("code"->$code,"message"->$message));
echo json_encode($response);
}
mysqli_close($con);

?>
