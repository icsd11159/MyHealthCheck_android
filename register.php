<?php
require "conn.php";
$username=$_POST['user_name'];
$password=$_POST['password'];
$fname=$_POST['name_user'];
$lname=$_POST['surname_user'];
$amka=$_POST['amka_user'];
$usermail=$_POST['email_user'];

$sql="SELECT * FROM users WHERE username='$fname' OR usermail='$usermail'OR amka='$amka' ";
$result=mysqli_query($conn,$sql);

if(mysqli_num_rows($result))
{
		$code="reg_failed";
		$message="User already exist";
		array_push($result,array("code"->$code,"message"->$message));
		echo json_encode($result);
}
else
	
{
	$role=1;//giati einai asthenis
	$hashedPassword = md5($password);
	 $query = "insert into users 
                            (
                                fname,
                                lname,
								username,
								usermail,
                                password,
                                role,
								amka
                            ) 
                            Values
                            (
                                '$fname',
                                '$lname',
                                '$username',
								'$usermail',
                                '$hashedPassword',
                                 $role,
								 $amka
                            )";
    $result = mysqli_query($conn, $query);

    if ($result) {
        mysqli_commit($conn);
       echo"Thank you for register . You can log in to My HealthCheck and see your examinations";
       
    } else {
        echo"Not successfull Sign up";
       
    }
		
}
		
		mysqli_close($conn);
		?>