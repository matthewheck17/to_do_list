<?php

require_once("DAO.class.php");
require_once("../data-classes/Task.class.php");

class TaskDAO extends DAO {

    protected function getSelectStatement()
    {
        return "SELECT * FROM tasks";
    }

    protected function getSpecificSelectStatement($taskID)
    {
        return "SELECT * FROM tasks WHERE task_id=" . $taskID;
    }

    protected function getInsertStatement($taskObj)
    {
        if (is_a($taskObj, 'Task')){
            return 'INSERT INTO tasks (title,content) VALUES ("' . $taskObj->getTitle() . '","' . $taskObj->getContent() . '")';
        } else {
            throw new Exception('Is not a task object');
        }
    }

    protected function getUpdateStatement($taskObj)
    {
        if (is_a($taskObj, 'Task')){
            return 'UPDATE tasks SET title = "' . $taskObj->getTitle() .'", content = "' . $taskObj->getContent() .'", completed = "' . $taskObj->getCompleted() .'" WHERE task_id="' . $taskObj->getID() .'"';
        } else {
            throw new Exception('Is not a task object');
        }
    }


}