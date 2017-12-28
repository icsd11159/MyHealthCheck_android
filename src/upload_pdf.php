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
	<form action="search_c.php" method="post" enctype="multipart/form-data">
                  
                   
					    <input type="hidden" name="size" value="1000000" />
						  Ανεβάστε το pdf της εξέτασης:
                    <input type="file" name="file" />
					<button type="submit" name="upload">upload</button>
		</form>

<?php
if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['upload'])) {
		// $id_c= $_POST['id_c'];

		 
            $file = rand(1000,100000)."-".$_FILES['file']['name'];
		 $file_loc = $_FILES['file']['tmp_name'];
		$file_size = $_FILES['file']['size'];
		$file_type = $_FILES['file']['type'];
	//	$folder="uploads/";
 $target_dir = "uploads/";
    $folder = $target_dir . basename($_FILES["file"]["name"]);
		move_uploaded_file($_FILES['file']['tmp_name'],$folder);

  $sql="INSERT INTO examination (file,type_f,size) VALUES('$file','$file_type','$file_size') WHERE i_ed='$id_e'";
    $result = mysqli_query($link, $sql);
  if ($result) {
        mysqli_commit($link);
      
		  showAlertDialogMethod("Success upload file");
        } else {
            mysqli_rollback($link);
            showAlertDialogMethod("Something goes wrong in uploading");
            return false;
        }
		}
		?>
	