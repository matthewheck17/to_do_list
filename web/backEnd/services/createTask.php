<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

header('Content-Type: application/json');

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new DatabaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

$json = file_get_contents('php://input');  // Takes raw data from the request
$data = json_decode($json); // Converts it into a PHP object

if (!empty($data->title) && !empty($data->content)) { //check that the title and content are not empty
    $newTask = new Task(null, $data->title, $data->content, null);
    $resultID = $taskDAO->insert($newTask);
    $resultArr = $taskDAO->findByID($resultID);
    echo json_encode($resultArr);
} else {
    http_response_code(400);
}
?>