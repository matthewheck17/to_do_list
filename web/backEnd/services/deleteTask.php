<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");

$connectionValues = array("localhost", "root", "root", "to_do_list");
$adapter = new DatabaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

// If the ID exists in the database, send the 200 HTTP request and delete the Task from the DB
if ($taskDAO->findByID($_GET['id'])) {
  http_response_code(200);
  $taskDAO->delete($_GET['id']);
// Else, send the following HTTP request
} else {
  http_response_code(404);
}
?>