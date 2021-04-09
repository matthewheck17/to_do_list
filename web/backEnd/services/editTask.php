<?php
// Used for JSON pretty print
//header('Content-Type: application/json');

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new databaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

if (!$taskDAO->findByID($_GET['id'])) { //if the id does not exist in the DB
    header($_SERVER["SERVER_PROTOCOL"]." 404 Not Found");
} else {
    $updatedTask = new Task($_GET['id'], $_GET['title'], $_GET['content'], $_GET['completed']);
    $taskDAO->update($updatedTask);
}


// echo "<script>console.log('" . $_GET['title'] ."')</script>";
// echo "<script>console.log('" . $_GET['content'] ."')</script>";
// echo "<script>console.log('" . $_GET['completed'] ."')</script>";

//$resultArr = $userDAO->findByID($_GET['id']);
//echo json_encode($resultArr);
?>