<html>
<head>
    <?php
	require "utilities/connectWithDB.php";
    include_once "page_parts/head.php";
    ?>
</head>
<body class="container">
<?php
include_once "page_parts/header.php";


?>

<?php
include_once "page_parts/login_checker.php";
?>

<div class="page_content">

    <form action="search_clients.php" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="keyword">Αναζήτηση Ασθενών:</label>
            <input required="required" type="text" class="form-control" id="keyword" name="keyword"
                   placeholder="Αμκα ασθενή, λέξεις κλειδία χωρισμένα με κενό">
        </div>
        <center>
            <button type="submit" name="search" class="btn btn-primary">Αναζήτηση</button>
        </center>
		 
     
    </form>
	
	 <form action="search_clients.php" method="post" enctype="multipart/form-data">
	   <center>
            <button type="submit" name="view_all" class="btn btn-primary">Εμφάνιση των Ασθενών</button>
        </center>
	</form>
    <br>
    <br>
    <table class="table">
        <?php

        if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['apply'])) {
            echo"<br> id:".$_SESSION['user_id'];

            $selected_thesis = mysqli_real_escape_string($link, $_POST['selected-thesis']);
            //showAlertDialogMethod("selected thesis id" . $selected_thesis);

            insert_thesis_apply_for_student($link, $selected_thesis, $_SESSION['user_id']);

            change_thesis_state($link, $selected_thesis, 2);



        }
	
		 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['new_examine'])) {
		 $id_doctor= $_SESSION['user_id'];
		

$amka = $_POST['new_examine'];
		
	
		 showAlertDialogMethod( "Amka client is :".$amka.".");
		 $_SESSION['amka'] = $amka;
		$id_c= $_POST['id_c'];
		 $_SESSION['id_c'] = $id_c;
		  echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
		 	 // echo '<input type="hidden" id="id_c" name="id_c" value="'.$id_c.'">;
                    echo '<input type="hidden" id="amka" name="amka" value="'.$amka.'">';
		 ?>

		
            <label for="lessons-selector">Τύπος Εξέτασης:</label>
            <div class="input-group"  >
                <select class="form-control" name="type" type="text" id="type"
                        style="margin-top: 10px;margin-bottom: 10px" >
 
                    <?php
                    

                    
                        echo '<option value="Ουρολογική"> Ουρολογική</option>';
						echo '<option value="Αιματολογική"> Αιματολογική</option>';
						echo '<option value="Κολπική"> Κολπική</option>';
                        echo '<option value="Ανοσολογική"> Ανοσολογική</option>';
					    echo '<option value="Αλλεργιολογική"> Αλλεργιολογική</option>';
					    echo '<option value="Βιοχημική"> Βιοχημική</option>';
					    echo '<option value="Καλλιέργεια"> Καλλιέργεια</option>';
					    echo '<option value="Ορμονική"> Ορμονική</option>';
					  
                    
					?>
		

	
        <div class="form-group">
            <label for="mail">Ημ/νία*:</label>
            <input required="required" type="date" class="form-control" id="date" name="date"
                   placeholder="Ημερομηνία Εξέτασης">
	
		 
	 
        </div>
		




 
                </select>
                <span class="input-group-btn">
                        <button type="submit"  id="submit"   name="submit" class="btn btn-success form-control">Προσθήκη</button>
					
                    </span>
            </div>
			
  
		 
			<?php
			

		 }
	
	 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['type']) || isset($_POST['add_again'])) {
		  $id_doctor= $_SESSION['user_id'];
		 $type =  $_POST['type'];
		   echo "Τύπος εξέτασης :",$type;
		   $_SESSION['type'] = $type;
		    $amka = $_SESSION['amka'];
$date =  $_POST['date'];
		   echo "Ημ/νία :",$date;
		   $_SESSION['date'] = $date;
					
				 	
   
	?>
	<div class="page_content">
	
	 <form action="search_clients.php" method="post" >
	 <button type="submit"  name="upload_exam"  id="upload_exam" class="btn btn-primary">Upload PDF Εξέτασης</button>
   </form>
   <form  method="post" action="search_clients.php" enctype="multipart/form-data">
   <div class="form-group" action="search_clients.php" method="post">
		<div class="form-group">
            <label for="lessons-selector">Όνομα Εξέτασης*:</label>
            <div class="input-group">
                <select class="form-control" name="text" type="text" id="text"
                        style="margin-top: 10px;margin-bottom: 10px" action="" >
                    <?php
					
    
                                   
                       if ($_POST['type']=="Ουρολογική"){
                        echo '<option value="pH"> pH</option>';
						echo '<option value="Ειδικό βάρος"> Ειδικό βάρος</option>';
						echo '<option value="Νάτριο"> Νάτριο</option>';
                        echo '<option value="Κάλιο"> Κάλιο</option>';
					    echo '<option value="Χλώριο"> Χλώριο</option>';
					    echo '<option value="Ασβέστιο"> Ασβέστιο</option>';
					    echo '<option value="Φωσφόρος"> Φωσφόρος</option>';
					    echo '<option value="Ουρία"> Ουρία</option>';
							}
							else if ($_POST['type']=="Αιματολογική"){
						 echo '<option value="Ερυθρά Αιμοσφαίρια (RBC)"> Ερυθρά Αιμοσφαίρια (RBC)</option>';
						echo '<option value="Αιμοσφαιρίνη (HGB)"> Αιμοσφαιρίνη (HGB)</option>';
						echo '<option value="Αιματοκρίτης Ht"> Αιματοκρίτης Ht</option>';
                        echo '<option value="Λευκά Αιμοσφαίρια (WBC)">Λευκά Αιμοσφαίρια (WBC)</option>';
					    echo '<option value="Ουδετερόφιλα % (NEUT)</">Ουδετερόφιλα % (NEUT)</option>';
					    echo '<option value="Ουδετερόφιλα (NEU)">Ουδετερόφιλα (NEU)</option>';
					    echo '<option value="Λεμφοκύτταρα % (LYMPH)"> Λεμφοκύτταρα % (LYMPH)</option>';
					    echo '<option value=" Ηωσινόφιλα %  (EO)"> Ηωσινόφιλα %  (EO)</option>';
							}
							else{
								echo '<option value="Ερυθρά Αιμοσφαίρια (RBC)"> Ερυθρά Αιμοσφαίρια (RBC)</option>';
						echo '<option value="Αιμοσφαιρίνη (HGB)"> Αιμοσφαιρίνη (HGB)</option>';
						echo '<option value="Αιματοκρίτης Ht"> Αιματοκρίτης Ht</option>';
                        echo '<option value="Λευκά Αιμοσφαίρια (WBC)">Λευκά Αιμοσφαίρια (WBC)</option>';
							}
							
			
			     
                    ?>
                </select>
                <span class="input-group-btn">
                        <button type="submit" id="text" name="text" action="" class="btn btn-success form-control">Προσθήκη</button>
                    </span>
            </div>
        </div>

		 
        <div class="form-group">
            <label for="amka">Αποτέλεσμα*:</label>
            <input required="required" type="text" class="form-control" id="result" name="result"
                   placeholder="Αποτέλεσμα">
        </div>
     
        <div class="form-group">
            <label for="history">Σχόλια:</label>
            <input required="required" type="text" class="form-control" id="comments" name="comments"
                   placeholder="Σχόλια">
        </div>
		

        <button type="submit" name="add_exam"  id="add_exam" class="btn btn-primary">Προσθήκη Εξέτασης </button>


    </form>
</div>
</div>
<?php

 
   
	 }
   	
			if (isset($_POST['upload_exam'])) {
				 $id_doctor= $_SESSION['user_id'];
		  $type = $_SESSION['type']; 
		 $amka= $_SESSION['amka'];
	
		//$id_c=get_user_by_amka($link, $amka);
			$id_c= $_SESSION['id_c'];
 
	echo " id client  ",$id_c;	
  
    $date =  $_SESSION['date'];
	?>
<div class="page_content">
   <form  method="post" action="search_clients.php" enctype="multipart/form-data">
   <div class="form-group" action="search_clients.php" method="post">
 <div class="form-group">
            <label for="history">Εισάγετε το path της εξέτασης(π.χ. Επιφάνεια εργασίας\εξεταση.pdf):</label>
		  <input required="required" type="text" class="form-control" id="path" name="path"
                   placeholder="Χωρις το  C:\Users\user\">
        </div>                 
    <span class="input-group-btn">
                        <button type="submit"  id="pathn"   name="pathn" class="btn btn-success form-control">Προσθήκη Path</button>
					
                    </span>	</div>
	     </div>
			</form>
<?php
//if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['uploads'])) {		

}
if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['pathn'])) {
	//$id_e=$_POST['$id_e'];
	  $path=$_POST['path'];
	echo("path : ") ,$path;	
	 $date_e=$_SESSION['date'];
	 $id_doctor= $_SESSION['user_id'];
		  $type = $_SESSION['type']; 
		 $amka= $_SESSION['amka'];
	 $query=" SELECT * FROM examines ORDER BY id_exam DESC LIMIT 1"; 
              $result = mysqli_query($link, $query) or die(mysqli_error($link));
			 // echo $row['amka'] ;
			// $id_exx;
			                            while ($row = $result->fetch_assoc()) {
											  echo "id previous:: ".$row['id_exam'] ;
											  // echo "  namep:: ".$row['name_p'] ;
											  $id_exam=$row['id_exam']+1;
											  
										}
									echo "now id new: ".$id_exam;
					echo " Put the username:' admin ' and password:' Panemorfi!1 ' ";				
   echo'<a target="_blank" href="http://desktop-r436uho:8080/api/rest/process/pdfexetaseisnew?filename='.$path.'&date_e='.$date_e.'&id_doctor='.$id_doctor.'&type='.$type.'&amka='.$amka.'&id_exam='.$id_exam.'">Καντε extract το pdf της εξέτασης</a>';

 
		//$id_c=get_user_by_amka($link, $amka);
	$id_c= $_SESSION['id_c'];


		 //echo "<p><a target='_blank' href='insert_from_pdf.php?  value1=$id_doctor&value2=$type&value3=$amka&value4=$date_e'>Ολοκλήρωση καταχώρησης ολης της εξέτασης απο PDF </a>";
	 } 

		 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['add_exam'])) {
		   $id_doctor= $_SESSION['user_id'];
		  $type = $_SESSION['type']; 
		 $amka= $_SESSION['amka'];
	   $date =  $_SESSION['date'];
		//$id_c=get_user_by_amka($link, $amka);
			$id_c= $_SESSION['id_c'];
 
	echo " id c  ",$id_c;	
    $results =$_POST['result'];
    $text =  $_POST['text'];
    $comments = $_POST['comments'];
   // $date =  $_POST['date'];
   
    $query=" SELECT * FROM examines ORDER BY id_exam DESC LIMIT 1"; 
              $result = mysqli_query($link, $query) or die(mysqli_error($link));
     while ($row = $result->fetch_assoc()) {
		 if($type==$row['type_ex'] && $id_doctor==$row['id_doctor']&& $amka==$row['amka']&& $date==$row['date_e']){
			  $id_exam=$row['id_exam'];//gia na exei to idio id h mia eksetash(kai oxi h kathe parametros ths diafretiko id)
		 }
		 else{
											  echo " id previous : ".$row['id_exam'] ;
											  // echo "  namep:: ".$row['name_p'] ;
											  $id_exam=$row['id_exam']+1;
											    $querytwo = "insert into upload_pdf (id_exam,amka) values ('$id_exam','$amka')";
    $resulttwo = mysqli_query($link, $querytwo) ;
	if ($resulttwo) {
	       mysqli_commit($link);
		  showAlertDialogMethod("Εισαγωγή νέας ομάδας εξέτασης");
	 
	
 
		
        } else {
            mysqli_rollback($link);
            showAlertDialogMethod("Αδυναμία εισαγωγής νέας ομάδας εξέτασης");
        
        }
	
		 }
											  
										}
	
    if (empty($id_exam) ||empty($amka) || empty($id_doctor) || empty($type) || empty($results) || empty($text)  || empty($date)  ) {
        showAlertDialogMethod("Συμπληρωστε τα πεδία");
       // exit();
    }
	
 $name_p=$text . ' ' .$results;
 echo "  Ονομα εξτασης  ".$name_p;
  echo " Id εξετασης  ".$id_exam;
      $id_e = add_exams($link, $id_exam, $id_doctor, $amka, $type, $name_p , $date , $comments);
		 if($id_e==true){
			 showAlertDialogMethod("done");
			  
  
 echo'<form action="" method="post">';
 echo'<input type="submit" name="add_again" value="Προσθήκη και άλλης παραμέτρους εξέτασης">';
  echo'<input type="hidden" id="type" name="type" value="'.$type.'">';
 echo'<input type="hidden" id="date" name="date" value="'.$date.'">';
  echo'<input type="hidden" id="amka" name="amka" value="'.$amka.'">';
 echo'</form>';
			 		 
		 ?>
		 	<a href="upload_pdf.php?id_e=<?php echo $id_exam?>">Ανεβάστε το pdf της εξέτασης:</a>
			<?php
		 }
		 else{
			 showAlertDialogMethod("not done");
		 }

          

}
 


		  if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['view_all'])) {
            $all_clients = get_client($link);
			
            if ($all_clients == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα</h5>';


            } else {
                echo ' <tr>';
				echo '<td><h4>Id Ασθενούς</h4></td>';
                echo '<td><h4>Όνομα</h4></td>';
                echo '<td><h4>Επώνυμο</h4></td>';
                echo '<td><h4>ΑΜΚΑ</h4></td>';
                echo '<td><h4>E-mail</h4></td>';
                echo '<td><h4>Ιστορικό</h4></td>';
                echo '</tr>';
                while ($row = $all_clients->fetch_assoc()) {
                    echo '<tr>';
					echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['id_c'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['cname'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['csurname'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['amka'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['mail'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['history'] . '</h5>';
                    echo '</td>';
                   
                    
                    echo '<td>';
                    echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
                   // echo ' <input type="hidden" id="selected-thesis" name="selected-thesis" value="' . $row['id'] . '">';
                   // echo ' <input type="hidden" id="selected-teacher-id" name="selected-teacher-id" value="' . $row['teacher_id'] . '">';
                

                    echo '<button type="submit" name="examine" class="btn btn-primary">Εξετάσεις Ασθενούς</button>';
				    echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
					echo '<input type="hidden" id="id_c" name="id_c" value="' . $row['id_c'] . '">';
                    echo '<input type="hidden" id="amka" name="amka" value="' . $row['amka'] . '">';
                     
					echo '<button type="submit" value="'.$row['amka'].'"   name="new_examine" class="btn btn-primary">Νέα Εξέταση</button>';
                    echo '</form>';
                    echo '</td>';
                    echo '</tr>';

                }
		   }
		}

        if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['search'])) {

            
            $keyword = mysqli_real_escape_string($link, $_POST['keyword']);


            $all_clients = get_patient_with_keywords($link, $keyword);

            if ($all_clients == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα</h5>';


            } else {
                echo ' <tr>';
                echo '<td><h4>Όνομα</h4></td>';
                echo '<td><h4>Επώνυμο</h4></td>';
                echo '<td><h4>ΑΜΚΑ</h4></td>';
                echo '<td><h4>E-mail</h4></td>';
                echo '<td><h4>Ιστορικό</h4></td>';
                echo '</tr>';
                while ($row = $all_clients->fetch_assoc()) {
                    echo '<tr>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['cname'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['csurname'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['amka'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['mail'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['history'] . '</h5>';
                    echo '</td>';
                   
                    
                    echo '<td>';
                    echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
                   // echo ' <input type="hidden" id="selected-thesis" name="selected-thesis" value="' . $row['id'] . '">';
                   // echo ' <input type="hidden" id="selected-teacher-id" name="selected-teacher-id" value="' . $row['teacher_id'] . '">';
               $amka=$row['amka'];
			  


//$_SESSION['new_examine'] = $row['amka'];


                    echo '<button type="submit" name="examine" class="btn btn-primary">Εξετάσεις Ασθενούς</button>';
					echo '<button type="submit" value="'.$amka.'"  name="new_examine" class="btn btn-primary">Νέα Εξέταση</button>';
                    echo '</form>';
                    echo '</td>';
                    echo '</tr>';
                }
            }
        }
		 ?>
       
    </table>

</div>

</body>
</html>

