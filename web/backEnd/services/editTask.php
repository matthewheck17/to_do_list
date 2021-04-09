<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

$connectionValues = array("localhost", "root", "root", "to_do_list");

$adapter = new databaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

if (!$taskDAO->findByID($_GET['id'])) { //if the id does not exist in the DB
    http_response_code(404);
} else {
    $updatedTask = new Task($_GET['id'], $_GET['title'], $_GET['content'], $_GET['completed']);
    $taskDAO->update($updatedTask);
}
?>
