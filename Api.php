

<?php 
 header("content-type: text/html;charset=utf-8");

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
$conn->set_charset('utf8');
 //Checking if any error occured while connecting
 if (mysqli_connect_errno()) {
 echo "Failed to connect to MySQL: " . mysqli_connect_error();
 die();
 }

 $amka_user = $_POST["amka_user"];
 //creating a query
 $stmt = $conn->prepare("SELECT amka, id_doctor, id_exam, type_ex, text, date_e ,comments FROM examines WHERE amka='$amka_user'");
 //$stmta = $conn->prepare("SELECT name FROM upload_pdf WHERE amka='$amka_user'");
 //executing the query 
 $stmt->execute();
 
 //binding results to the query 
 $stmt->bind_result($amka, $id_doctor, $id_exam, $type_ex, $text, $date_e ,$comments);
 // $stmta->execute();
 
 //binding results to the query 
 //$stmta->bind_result($name);
 $examines = array(); 
 // $files = array();
 //traversing through all the result 
 while($stmt->fetch()){
	//  $temp = array(); 
 $temp=[
'amka'=>$amka,
'id_doctor'=>$id_doctor, 
'id_exam'=>$id_exam, 
'type_ex'=>$type_ex, 
'text'=>$text, 
'date_e' =>$date_e, 
'comments'=>$comments,
];
 array_push($examines, $temp);
 
 }
  //while($stmta->fetch()){
	//  $temp = array(); 
 //$tempa=[
//'name'=>$name,
//];
 //array_push($examines, $temp);
 //array_push($files, $tempa);

 //displaying the result in json format 
 echo json_encode($examines);
 // echo json_encode($files);
 
  
