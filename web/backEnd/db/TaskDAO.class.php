<?php

require_once("DAO.class.php");
require_once("../data-classes/Task.class.php");

class TaskDAO extends DAO {

    protected function getSelectStatement()
    {
        return "SELECT * FROM tasks";
    }

    protected function getSpecificSelectStatement()
    {   
        return "SELECT * FROM tasks WHERE task_id=?";
    }

    protected function getInsertStatement()
    {

        return 'INSERT INTO tasks (title,content) VALUES (?,?)';
    }

    protected function getUpdateStatement()
    {
        return 'UPDATE tasks SET title = ?, content = ?, completed = ? WHERE task_id=?';
    }

    protected function getDeleteStatement()
    {
        return "DELETE FROM tasks WHERE task_id=?";
    }


}