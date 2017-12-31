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
 
 //creating a query
 $stmt = $conn->prepare("SELECT amka, id_d, type, name_exam, result, date ,comments FROM examination");
 
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($amka, $id_d, $type, $name_exam, $result, $date ,$comments);
 
 $examines = array(); 
 
 //traversing through all the result 
 while($stmt->fetch()){
	  $temp = array(); 
 $temp=[
'amka'=>$amka,
'id_d'=>$id_d, 
'type'=>$type, 
'name_exam'=>$name_exam, 
'result'=>$result,
'date' =>$date, 
'comments'=>$comments
];
 array_push($examines, $temp);
 }
 
 //displaying the result in json format 
 echo json_encode($examines);
 