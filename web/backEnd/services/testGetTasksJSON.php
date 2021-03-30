<?php
    include "../db/DatabaseAdapterMySQLI.class.php";
    include "../db/TaskDAO.class.php";

    $connectionValues = array("localhost", "root", "root", "to_do_list");

    $adapter = new databaseAdapterMySQLI($connectionValues);
    $userDAO = new TaskDAO($adapter);
    $resultArr = $userDAO->findAll();
    //$resultArr = $userDAO->findByID(2); This also works example
    echo json_encode($resultArr);