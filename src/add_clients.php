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

<?php

if ($_SERVER['REQUEST_METHOD'] == "POST" && isset($_POST['add'])) {
    $cname = mysqli_real_escape_string($link, $_POST['cname']);
    $csurname = mysqli_real_escape_string($link, $_POST['csurname']);
    $amka = mysqli_real_escape_string($link, $_POST['amka']);
    $mail = mysqli_real_escape_string($link, $_POST['mail']);
    $history = mysqli_real_escape_string($link, $_POST['history']);


    if (empty($cname) || empty($csurname) || empty($amka) || empty($mail) ) {
        showAlertDialogMethod("Συμπληρωστε τα πεδία");
        exit();
    }

     add_client($link, $cname, $csurname, $amka, $mail, $history);
}
?>
<div class="page_content">
    <form action="add_clients.php" method="post" enctype="multipart/form-data">
        <div class="form-group">
            <label for="cname">Όνομα Ασθενούς*:</label>
            <input required="required" type="text" class="form-control" id="cname" name="cname" placeholder="Όνομα">
        </div>
        <div class="form-group">
            <label for="csurname">Επίθετο Ασθενούς*:</label>
            <input required="required" type="text" class="form-control" id="csurname" name="csurname"
                   placeholder="Επίθετο">
        </div>
        <div class="form-group">
            <label for="amka">ΑΜΚΑ Ασθενούς*:</label>
            <input required="required" type="text" class="form-control" id="amka" name="amka"
                   placeholder="ΑΜΚΑ">
        </div>
        <div class="form-group">
            <label for="mail">E-mail Ασθενούς*:</label>
            <input required="required" type="text" class="form-control" id="mail" name="mail"
                   placeholder="Ε-mail Ασθενούς">
        </div>
        <div class="form-group">
            <label for="history">Ιστορικό Ασθενειών:</label>
            <input required="required" type="text" class="form-control" id="history" name="history"
                   placeholder="Ιστορικό Ασθενούς">
        </div>



        <button type="submit" name="add" id="add" class="btn btn-primary">Προσθήκη Ασθενούς</button>

    </form>
</div>
</body>
