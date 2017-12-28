<html>
<head>
    <?php
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
		// $id_c= $_POST['id_c'];
		  echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
		 	 // echo '<input type="hidden" id="id_c" name="id_c" value="'.$id_c.'">;
                    echo '<input type="hidden" id="amka" name="amka" value="'.$amka.'">';
		 ?>
<div class="page_content">
    <form action="search_clients.php" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="lessons-selector">Τύπος Εξέτασης:</label>
            <div class="input-group">
                <select class="form-control" name="type" type="text" id="type"
                        style="margin-top: 10px;margin-bottom: 10px">
                    <?php
                    

                    
                        echo '<option value="ourologikh"> Ουρολογική</option>';
						echo '<option value="aimatologikh"> Αιματολογική</option>';
						echo '<option value="kolpikh"> Κολπική</option>';
                    
                    ?>
                </select>
                <span class="input-group-btn">
                        <button type="button" id="type" name="type" class="btn btn-success form-control">Προσθήκη</button>
                    </span>
            </div>
        </div>
        <div class="form-group">
            <label for="csurname">Όνομα Εξέτασης*:</label>
            <input required="required" type="text" class="form-control" id="name_exam" name="name_exam"
                   placeholder="Όνομα Εξέτασης">
        </div>
		   
        <div class="form-group">
            <label for="amka">Αποτέλεσμα*:</label>
            <input required="required" type="text" class="form-control" id="result" name="result"
                   placeholder="Αποτέλεσμα">
        </div>
        <div class="form-group">
            <label for="mail">Ημ/νία*:</label>
            <input required="required" type="date" class="form-control" id="date" name="date"
                   placeholder="Ημερομηνία Εξέτασης">
	
	 
        </div>
        <div class="form-group">
            <label for="history">Σχόλια:</label>
            <input required="required" type="text" class="form-control" id="comments" name="comments"
                   placeholder="Σχόλια">
        </div>
		

        <button type="submit" name="add_exam" id="add_exam" class="btn btn-primary">Προσθήκη Εξέτασης και Εγγράφου</button>


    </form>
</div>
<?php
}
 
		

        
    
		 

		 
		 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['add_exam'])) {
		   $id_doctor= $_SESSION['user_id'];
		 $amka= $_POST['amka'];
		
		    $type =  $_POST['type'];
    $result =$_POST['result'];
    $name_exam =  $_POST['name_exam'];
    $comments = $_POST['comments'];
    $date =  $_POST['date'];
	
    if (empty($type) || empty($result) || empty($name_exam) || empty($comments) || empty($date)  ) {
        showAlertDialogMethod("Συμπληρωστε τα πεδία");
        exit();
    }
	
	
           $id_e= add_exams($link, $id_doctor, $amka, $type, $name_exam, $result, $date , $comments);
		 
		 
            if ($id_e == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα</h5>';


            } else {
               
					 while ($row = $id_e->fetch_assoc())
				{
					 showAlertDialogMethod("id εξετασης".$row['id_e'].".");
				}
				   }
?>
		
		<?php
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
				$amka=$row['amka'];
			   $id_e=$row['id_e'];


//$_SESSION['new_examine'] = $row['amka'];


                  //  echo '<button type="submit" name="examine" class="btn btn-primary">Εξετάσεις Ασθενούς</button>';
					echo '<button type="submit" value="'.$amka.'"  name="new_examine" class="btn btn-primary">Νέα Εξέταση</button>';
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

