<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");

header('Content-Type: application/json'); // Used for JSON pretty print

$connectionValues = array("localhost", "root", "root", "to_do_list");
$adapter = new DatabaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

// If the ID exists in the database, send the HTTP request and display the JSON results
if ($taskDAO->findByID($_GET['id'])) {
  http_response_code(200);
  $resultArr = $taskDAO->findByID($_GET['id']);
  echo json_encode($resultArr);
// Else, send the following HTTP request
} else {
  http_response_code(404);
}
?>
