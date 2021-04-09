<?php

header('Content-Type: application/json');

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new databaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

if (isset($_GET['title']) && isset($_GET['content'])) { //check that the title and content are provided in the query string
    $newTask = new Task(null, $_GET['title'], $_GET['content'], null);
    $resultID = $taskDAO->insert($newTask);
    $resultArr = $taskDAO->findByID($resultID);
    echo json_encode($resultArr);
} else {
    http_response_code(400);
}
?>