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
 //creating a query
 $stmt = $conn->prepare("SELECT fname,lname,amka,SOSnumber,history FROM users WHERE amka='$amka_user'");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($fname,$lname,$amka,$SOSnumber,$history);
 
 $examines = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
 if($SOSnumber==null|| $history==null){
	$SOSnumber='0';
	$history='0';
 }
 //$temp = array(); 
 $temp=[
'fname'=>$fname,
'lname'=>$lname,
'amka'=>$amka,
'SOSnumber'=>$SOSnumber, 
'history'=>$history
];
	
 array_push($examines, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($examines);
 