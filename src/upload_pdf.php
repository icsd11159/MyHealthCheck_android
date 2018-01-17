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
<?php
$id_e = $_GET['id_e'];
$_POST['$id_e']=$id_e;

echo "ide:",$id_e;

//$_POST['$id_e']=$id_e;
?>

		<div class="page_content">

<form action="" method="post" enctype="multipart/form-data">
                  
                   
					    <input type="hidden" name="size" value="1000000" />
						
						 Ανεβάστε το pdf της εξέτασης:
                   <input type="file" name="file" />
                       <input type="hidden" name="id_e" value="'.$id_e.'" />
					<button type="submit"  name="upload">upload</button>
		</form>

<?php
//if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['uploads'])) {		


if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['upload'])) {
	//$id_e=$_POST['$id_e'];
	
	$id_e=$_POST['$id_e'];
echo "ide2 now :",$id_e;
		 
            $file = rand(1000,100000)."-".$_FILES['file']['name'];
		 $file_loc = $_FILES['file']['tmp_name'];
		$file_size = $_FILES['file']['size'];
		$file_type = $_FILES['file']['type'];
	//	$folder="uploads/";
	$id=(string)$id_e;
	//$id_e=$id+"/";
	if (!file_exists("".$id."")) {
    mkdir("".$id."", 0777, true);
}

 $target_dir = ("".$id."/");
    $folder = $target_dir . basename($_FILES["file"]["name"]);
		move_uploaded_file($_FILES['file']['tmp_name'],$folder);
$id_e = mysqli_real_escape_string($link,$id_e);
  $sql="UPDATE examination SET file='$file',type_f='$file_type',size='$file_size' WHERE id_e='$id_e'";
   $result = $link->query($sql);
  // $result = mysqli_query($link, $sql);
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

	
		</div>
		</body>
		</html>
	