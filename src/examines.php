<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <?php
    include_once "page_parts/head.php";
    ?>
  
</head>
<body class="container">
<?php
require "utilities/connectWithDB.php";
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
	 <form action="examines.php" method="post" enctype="multipart/form-data">
	   <center>
            <button type="submit" name="normal" class="btn btn-primary">Αυτόματη εισαγωγή φυσιολογικών τιμών</button>
        </center>
	</form>
    <br>
    <table class="table">
        <?php
         //  session_start();
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
		 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['edit_value'])) {
  $id=$_POST['id'];
  $id_e=$_POST['id_e'];
 $id_d= $_SESSION['user_id'];
  $value=$_POST['value'];
   $edit_text=$_POST['edit_text'];
	mysqli_autocommit($link, false);													 
    $sql = "UPDATE examines
            SET $value='$edit_text'
            WHERE id_exam='$id_e' AND id='$id'";
		 $result = mysqli_query($link, $sql);
    if ($result) {
        mysqli_commit($link);
         showAlertDialogMethod("Επιτυχής Επεξεργασία");
        return true;
    } else {
        mysqli_rollback($link);
         showAlertDialogMethod("Ανεπιτυχής Επεξεργασία");
        return false;
    }
}
		 	if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['delete'])) {
  $id_d= $_SESSION['user_id'];
  $id_e=$_POST['id_e'];
  $text=$_POST['text'];
    
     $result =delete_exam($link,$id_e,$text);
			}
					 	if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['edit_e'])) {
							  $id_d= $_SESSION['user_id'];
							   $id= $_POST['id'];
  $id_e=$_POST['id_e'];
  $text=$_POST['text'];
  	$value=$_POST['edit_the'];
  echo "Επεξεργασία για το πεδίο :".$value;
  
 echo'<form action="" method="post">';
 echo'<input type="text" name="edit_text">';
 echo'<input type="submit" name="edit_value">';
 echo'<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
 echo'<input type="hidden" id="id" name="id" value="'.$id.'">';
 echo'<input type="hidden" id="value" name="value" value="'.$value.'">';
 echo'</form>';
	
	

    
     //$result =delete_exam($link,$id_e,$text);
			}
		if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['edit'])) {
  $id_d= $_SESSION['user_id'];
  $id_e=$_POST['id_e'];
  $text=$_POST['text'];
  
     $result =get_exam_for_edit($link,$id_e,$text);
  echo ' <tr>';
                echo '<td><h4>AMKA</h4></td>';
				echo '<td><h4>Τύπος Εξέτασης</h4></td>';
				echo '<td><h4>Αποτέλεσμα</h4></td>';
				echo '<td><h4>Ημερομηνία</h4></td>';
				echo '<td><h4>Σχόλια</h4></td>';
                echo '</tr>';
       
				
            
                    
                          while ($row = $result ->fetch_assoc()) { 
						   $id=$row['id'];
	 
						          echo '<tr>';
    
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['amka'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['type_ex'] . '</h5>';
						 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit_e" value="Επεξεργασία"/>';
		  echo '<input type="hidden" id="edit_the" name="edit_the" value="type_ex">';
		   echo '<input type="hidden" id="id_e" name="id_e" value="'.$id.'">';
  echo '<input type="hidden" id="id" name="id" value="'.$id_e.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['text'] . '</h5>';
						 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit_e" value="Επεξεργασία"/>';
		  echo '<input type="hidden" id="edit_the" name="edit_the" value="text">';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
    echo '<input type="hidden" id="id" name="id" value="'.$id.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['date_e'] . '</h5>';
						echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit_e" value="Επεξεργασία"/>';
		 echo '<input type="hidden" id="id" name="id" value="'.$id.'">';
		  echo '<input type="hidden" id="edit_the" name="edit_the" value="date">';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['comments'] . '</h5>';
	 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit_e" value="Επεξεργασία"/>';
		  echo '<input type="hidden" id="edit_the" name="edit_the" value="comments">';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
   echo '<input type="hidden" id="id" name="id" value="'.$id.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
                    echo '</td>';
                    
                    echo '<td>';
                 
                    echo '</td>';
					 echo '<td>';
					
		 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="delete" value="Διαγραφή"/>';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
	
	echo'</td>';
              echo' </tr>';
		
					}
                
 
 
 }
		
      
		  if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['view_all'])) {
          	 $id_d= $_SESSION['user_id'];
			$id_e=0;//default
			$all_examines= get_exam_by_client($link,  $id_d);
			// mysqli_autocommit($link, false);
          
            if ($all_examines == null) {

                echo '<h5>Δεν βρέθηκαν αποτελέσματα </h5>';


            } else {
                echo ' <tr>';
                echo '<td><h4>AMKA</h4></td>';
				echo '<td><h4>Τύπος Εξέτασης</h4></td>';
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
                    echo '<h5 id="align_start" style="">' . $row['type_ex'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['text'] . '</h5>';
					
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['date_e'] . '</h5>';

                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['comments'] . '</h5>';
	
                    echo '</td>';
                    
                    echo '<td>';
                    echo '<form action="examines.php" method="post" enctype="multipart/form-data">';

               	 echo '<form action="search_clients.php" method="post" enctype="multipart/form-data">';
                  
                   
					
		 echo '</form>';
                
				
                    echo '</form>';
                    echo '</td>';
					 echo '<td>';
					
				
					
		$text=$row['text'];
		if($id_e!=$row['id_exam']){
							  $id_e=$row['id_exam'];
					 ?>
		<a href="upload_pdf.php?id_e=<?php echo $id_e ?>">Ανεβάστε το pdf της εξέτασης:</a>
		<?php
		echo'</form>';
		 echo "ID Examination: ".$id_e;		
				}
				
			 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit" value="Επεξεργασία/Διαγραφή"/>';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
					
				
		
	echo'</td>';
              echo' </tr>';
		
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
                    echo '<h5 id="align_start" style="">' . $row['type_ex'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['text'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['date_e'] . '</h5>';
                    echo '</td>';
                    echo '<td>';
                    echo '<h5 id="align_start" style="">' . $row['comments'] . '</h5>';
                    echo '</td>';
                    
                    echo '<td>';
               
                    echo '</td>';
					 echo '<td>';
				
					 echo "id εξετασης: ",$id_e;
					   $_SESSION['id_e'] = $id_e;
					
						
                  
					if($id_e!=$row['id_exam']){
							  $id_e=$row['id_exam'];

					 ?>
		<a href="upload_pdf.php?id_e=<?php echo $id_e ?>">Ανεβάστε το pdf της εξέτασης:</a>
		<?php
       echo "ID Examination: ".$id_e;
						 }
		 echo '	<form method="post" action="">';
        echo ' <input type="submit" name="edit" value="Επεξεργασία/Διαγραφή"/>';
  echo '<input type="hidden" id="id_e" name="id_e" value="'.$id_e.'">';
	 echo '<input type="hidden" id="text" name="text" value="'.$text.'">';
     echo '  </form>';
		 echo '</td>';
                    echo '</tr>';
					}
                }
            
        }
		 if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['normal'])) {
			 $id_d= $_SESSION['user_id'];
			 $all_examines= get_exam_by_client($link, $id_d );
			  while ($row = $all_examines->fetch_assoc()) { 
			 $my_result =$row['text'];
			  $id_ex=$row['id_exam'];
			  
			 $examines=get_normal_ex($link,$row['type_ex'],$row['amka'],$my_result,$id_ex );
		   
				 
			}}
			  

		 
        ?>
    </table>
</div>

</body>
</html>

