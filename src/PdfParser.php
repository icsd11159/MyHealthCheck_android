 <html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<?php


    
    include_once "page_parts/head.php";
	include ( 'PdfToText.phpclass' ) ;
    ?>

</head>
<body class="container">
<?php
include_once "page_parts/header.php";
?>
 <div class="page_content">
  
<form action="PdfParser.php" method="post" enctype="multipart/form-data">
    Select image to upload:
    <input type="file" name="file" id="file">
    <input type="submit" value="Upload PDF" name="upload">
</form>



 <?php
if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['upload'])) {
 
  $target_dir = "uploads/";
    $target_file = $target_dir . basename($_FILES["file"]["name"]);
    $uploadOk = 1;
    $filename = $_FILES['file']['tmp_name'];
	$pdf	=  new PdfToText ( $filename ) ;
		echo $pdf -> Text ;		// or : echo ( string ) $pdf ;
if(move_uploaded_file($_FILES['file']['tmp_name'], $target_file))

 {

 echo "The file ". basename( $_FILES['file']['name']). "yes is uploaded";

 }

 else {

 echo "Problem uploading file man";

 }
 }
 ?>
 </body>
 </div>
 </html>