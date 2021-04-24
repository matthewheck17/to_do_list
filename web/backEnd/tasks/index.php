<?php

require_once("../db/DatabaseAdapterMySQLI.class.php");
require_once("../db/TaskDAO.class.php");
require_once("../data-classes/Task.class.php");

header('Content-Type: application/json');

$connectionValues = array("localhost", "root", "root", "to_do_list");
$adapter = new DatabaseAdapterMySQLI($connectionValues);
$taskDAO = new TaskDAO($adapter);

switch ($_SERVER['REQUEST_METHOD']) {
    case 'POST':
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
        break;
    case 'PUT':
        $json = file_get_contents('php://input');  // Takes raw data from the request
        $data = json_decode($json); // Converts it into a PHP object

        if (!isset($_GET["id"])) {
            http_response_code(400);
            break;
        }

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
        break;
    case 'GET':
        if(isset($_GET['id'])){
            if ($taskDAO->findByID($_GET['id'])) {  // If the ID exists in the database, send the  200 HTTP request and display the JSON results
                http_response_code(200);
                $resultArr = $taskDAO->findByID($_GET['id']);
                echo json_encode($resultArr);
            } else {  // Else, send the following HTTP request
                http_response_code(404);
            }
        } else {
            $resultArr = $taskDAO->findAll();
            if ($resultArr) {  // If the database is empty, throw the corresponding response code
                http_response_code(200);
                echo json_encode($resultArr);
            }
        }
        break;
    case 'DELETE':
        if (isset($_GET['id'])){
            if ($taskDAO->findByID($_GET['id'])) {  // If the ID exists in the database, send the 200 HTTP request and delete the Task from the DB
                http_response_code(200);
                $taskDAO->delete($_GET['id']);
            // Else, send the following HTTP request
            } else {
                http_response_code(404);
            }
        } else {
            http_response_code(400);
        }
        break;
    default:
        http_response_code(400);
        break;
}
?>