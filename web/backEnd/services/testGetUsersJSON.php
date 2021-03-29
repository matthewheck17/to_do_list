<?php
    include "../db/DatabaseAdapterMySQLI.class.php";
    include "../db/UserDAO.class.php";

    $connectionValues = array("localhost", "root", "root", "to_do_list");

    $adapter = new databaseAdapterMySQLI($connectionValues);
    $userDAO = new UserDAO($adapter);
    $resultArr = $userDAO->findAll();
    //$resultArr = $userDAO->findByID(1); This also works
    echo json_encode($resultArr);