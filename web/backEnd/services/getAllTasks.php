<?php
    require_once("../db/DatabaseAdapterMySQLI.class.php");
    require_once("../db/TaskDAO.class.php");
    // Displays output as JSON in prettyprint
    header('Content-Type: application/json');

    $connectionValues = array("localhost", "root", "root", "to_do_list");

    $adapter = new DatabaseAdapterMySQLI($connectionValues);
    $taskDAO = new TaskDAO($adapter);
    $resultArr = $taskDAO->findAll();
    // If the database is empty, throw the corresponding response code
    if (!$resultArr) {
      http_response_code(404);
    // Otherwise, echo the results from the database
    } else {
      http_response_code(200);
      echo json_encode($resultArr);
    }
