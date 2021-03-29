<?php

include "DAO.class.php";

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

    protected function getUpdateTaskStatement($taskObj)
    {
        //TODO
    }


}