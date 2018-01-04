
<?php 
require "conn.php";
$user_name = $_POST["user_name"];
$user_pass = $_POST["password"];
$hashed_password = md5($user_pass);
$mysql_qry = "SELECT amka FROM users WHERE username='$user_name' AND password='$hashed_password'";
$result = mysqli_query($conn ,$mysql_qry);
if(mysqli_num_rows($result) > 0) {
	while($row = mysqli_fetch_assoc($result)){
      $amka=$row['amka'];
echo  $amka;
	}
}
else {
echo "login not success";
}

?>


