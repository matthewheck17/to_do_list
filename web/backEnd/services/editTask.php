<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new DatabaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

$json = file_get_contents('php://input');  // Takes raw data from the request
$data = json_decode($json); // Converts it into a PHP object

if (!$taskDAO->findByID($_GET["id"])) { //if the id does not exist in the DB
    http_response_code(404);
} else {
    if (!empty($data->title) && !empty($data->content) && !empty($data->completed)) { //check that the title and content are not empty
        $updatedTask = new Task($_GET["id"], $data->title, $data->content, $data->completed);
        $taskDAO->update($updatedTask);
        http_response_code(200);
    } else {
        http_response_code(400);
    }
}
?>
