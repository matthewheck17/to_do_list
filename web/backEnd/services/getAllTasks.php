<?php
    require_once("../db/DatabaseAdapterMySQLI.class.php");
    require_once("../db/TaskDAO.class.php");
    // Displays output as JSON in prettyprint
    header('Content-Type: application/json');

    $connectionValues = array("localhost", "root", "root", "to_do_list");

    $adapter = new databaseAdapterMySQLI($connectionValues);
    $taskDAO = new TaskDAO($adapter);
    $resultArr = $taskDAO->findAll();
    echo json_encode($resultArr);
