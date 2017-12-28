<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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

    <form action="examines.php" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="keyword">Αναζήτηση Εξετάσεων:</label>
            <input required="required" type="text" class="form-control" id="keyword" name="keyword"
                   placeholder="Αμκα ασθενή, λέξεις κλειδία χωρισμένα με κενό">
        </div>
        <center>
            <button type="submit" name="search" class="btn btn-primary">Αναζήτηση</button>
        </center>
		 
     
    </form>
	 <form action="examines.php" method="post" enctype="multipart/form-data">
	   <center>
            <button type="submit" name="view_all" class="btn btn-primary">Εμφάνιση των Εξετάσεων</button>
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
		 $amka= $_POST['amka'];
		 $id_c= $_POST['id_c'];
		  echo '<form action="examines.php" method="post" enctype="multipart/form-data">';
		 	  echo '<input type="hidden" id="id_c" name="id_c" value="'.$id_c.'">';
                    echo '<input type="hidden" id="amka" name="amka" value="'.$amka.'">';

echo '</form>';
		 }
		
		
      
		  if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['view_all'])) {
          	 $id_d= $_SESSION['user_id'];
			
			$all_examines= get_exam_by_client($link,  $id_d);
            

            if ($all_examines == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα </h5>';


            } else {
                echo ' <tr>';
                echo '<td><h4>AMKA</h4></td>';
				echo '<td><h4>Τύπος Εξέτασης</h4></td>';
				echo '<td><h4>Όνομα Εξέτασης</h4></td>';
				echo '<td><h4>Αποτέλεσμα</h4></td>';
				echo '<td><h4>Ημερομηνία</h4></td>';
				echo '<td><h4>Σχόλια</h4></td>';
                echo '</tr>';
       
				
            
                    
                          while ($row = $all_examines->fetch_assoc()) { 
					
						          echo '<tr>';
    
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['amka'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['type'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['name_exam'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['result'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['date'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['comments'] . '</h5>';
                    echo '</td>';
                    
                    echo '<td>';
                    echo '<form action="examines.php" method="post" enctype="multipart/form-data">';
               	 echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
                  
                   
					    echo ' <input type="hidden" name="size" value="1000000" />';
                     echo '<input type="file" name="file" />';
					 echo '<button type="submit" name="btn-upload">upload</button>';
		 echo '</form>';
                    echo '<button type="submit" name="examine" class="btn btn-primary">Εξετάσεις Ασθενούς</button>';
					echo '<button type="submit" value="' . $row['amka'] . '" name="new_examine" class="btn btn-primary">Νέα Εξέταση</button>';
                    echo '</form>';
                    echo '</td>';
					 echo '<td>';
						echo '<form action="upload_pdf.php" method="post" enctype="multipart/form-data">';
                  
                   
					    echo '<input type="hidden" name="size" value="1000000" />';
						 echo " Ανεβάστε το pdf της εξέτασης:";
                    echo '<input type="file" name="file" />';
					echo '<button type="submit" name="upload">upload</button>';
		echo '</form>';
		 echo '</td>';
                    echo '</tr>';
					}
                }
		   
		}

        if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['search'])) {

            
            $keyword = mysqli_real_escape_string($link, $_POST['keyword']);
			 $id_d= $_SESSION['user_id'];
			$all_examines= get_examines_with_keywords($link, $keyword, $id_d );
            

            if ($all_examines == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα</h5>';


            } else {
                echo ' <tr>';
                echo '<td><h4>AMKA</h4></td>';
				echo '<td><h4>Τύπος Εξέτασης</h4></td>';
				echo '<td><h4>Όνομα Εξέτασης</h4></td>';
				echo '<td><h4>Αποτέλεσμα</h4></td>';
				echo '<td><h4>Ημερομηνία</h4></td>';
				echo '<td><h4>Σχόλια</h4></td>';
                echo '</tr>';
       
				
            
                    
                          while ($row = $all_examines->fetch_assoc()) { 
					
						          echo '<tr>';
    
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['amka'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['type'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['name_exam'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['result'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['date'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['comments'] . '</h5>';
                    echo '</td>';
                    
                    echo '<td>';
                    echo '<form action="examines.php" method="post" enctype="multipart/form-data">';

                    echo '<button type="submit" name="examine" class="btn btn-primary">Εξετάσεις Ασθενούς</button>';
					echo '<button type="submit" value="' . $row['amka'] . '" name="new_examine" class="btn btn-primary">Νέα Εξέταση</button>';
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

