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
Ανέβασε την υπογραφή σου
<?php
				                    echo '<td>';
                    echo '<form action="upload.php" method="post" enctype="multipart/form-data">';
                    echo '<input type="hidden" name="size" value="1000000" />';
                    echo ' <input type="file" name="image" />';
				                    echo '</form>';
                    echo '</td>';
  if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['upload'])) {
            echo"<br> id:".$_SESSION['user_id'];

            $selected_thesis = mysqli_real_escape_string($link, $_POST['selected-thesis']);
            //showAlertDialogMethod("selected thesis id" . $selected_thesis);

            insert_thesis_apply_for_student($link, $selected_thesis, $_SESSION['user_id']);

            change_thesis_state($link, $selected_thesis, 2);

            // TODO Teacher id
            $selected_teacher_id = mysqli_real_escape_string($link, $_POST['selected-teacher-id']);

            showAlertDialogMethod($selected_teacher_id);

            // TODO send mail to teacher
            $image = addslashes(file_get_contents($_FILES['image']['tmp_name'])); //SQL Injection defence!
            $image_name = addslashes($_FILES['image']['name']);
            $mime = mysqli_real_escape_string($link, $_FILES['image']['type']);

            echo "<br> name:" . $image_name;


            $target_dir = "C:/Users/User/Desktop/";
            $target_file = $target_dir . basename($_FILES["image"]["name"]);
            $uploadOk = 1;
            $imageFileType = pathinfo($target_file, PATHINFO_EXTENSION);

            // Check if file already exists
            if (file_exists($target_file)) {
                echo "Sorry, file already exists.";
                $uploadOk = 0;
            }
            // Check if $uploadOk is set to 0 by an error
            if ($uploadOk == 0) {
                echo "Sorry, your file was not uploaded.";
// if everything is ok, try to upload file
            } else {
                if (move_uploaded_file($_FILES["image"]["tmp_name"], $target_file)) {
                    echo "The file " . basename($_FILES["image"]["name"]) . " has been uploaded.";
                } else {
                    echo "Sorry, there was an error uploading your file.";
                }
            


        move_uploaded_file($_FILES['image']['tmp_name'], $target_dir. $_FILES['image']['name']);
        $image_f = $target_dir. $_FILES['image']['name'];
        echo"<br>path file : ".$image_f  ;
            $thesis= $_POST['selected-thesis'];
            echo'<br>iddd'.$thesis;
        $s="SELECT * FROM thesis WHERE id=$thesis" ;
        $result1=$link->query($s);

        if(mysqli_query($link,$s)) {

            while ($row1 = $result1->fetch_assoc()) {
                $id_t = $row1['teacher_id'];
            }
        }
        echo"<br>nah".$id_t;
            $s="SELECT * FROM user WHERE id=$id_t" ;
            $result1=$link->query($s);

            if(mysqli_query($link,$s)) {

                while ($row1 = $result1->fetch_assoc()) {
                    $email = $row1['email'];
                }
            }
            $address= $email;
        $path=$image_f; 
//        $address= (get_user_by_id($link,$selected_teacher_id))->email;
//        $path=$image_f;
        $message="uparxei aithsh egdhlwshs endiaferontos gia diplwmatikh apo foithth mpeite sto susthma me tis diplwmatikes";
        send_mail_to_user($address,$message,$path);
      }
	}
	?>
		</div>

</body>

</html>