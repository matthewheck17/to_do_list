<?php
// Used for JSON pretty print
header('Content-Type: application/json');

include "../db/DatabaseAdapterMySQLI.class.php";
include "../db/TaskDAO.class.php";

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new databaseAdapterMySQLI($connectionValues);
$userDAO = new TaskDAO($adapter);

$resultArr = $userDAO->findByID($_GET['id']);
echo json_encode($resultArr);
?>
