<?php 
 
 /*
 * Created by Belal Khan
 * website: www.simplifiedcoding.net 
 * Retrieve Data From MySQL Database in Android
 */
 
 //database constants
 define('DB_HOST', 'localhost');
 define('DB_USER', 'root');
 define('DB_PASS', '');
 define('DB_NAME', 'myhealthcheck');
 
 //connecting to database and getting the connection object
 $conn = new mysqli(DB_HOST, DB_USER, DB_PASS, DB_NAME);
 
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }
  $amka_user = $_POST["amka_user"];
  $number_sos = $_POST["number_sos"];
  $history=$_POST["history"];
 //creating a query
$stmt = $conn->prepare("UPDATE users SET SOSnumber='$number_sos' , history='$history' WHERE users.amka='$amka_user'");
 
//executing the query 
 $stmt->execute();
 

 
if($stmt) {
	
$result= "Success adding values";
}
else {
$result="Not adding the values";
}
 echo json_encode($result);