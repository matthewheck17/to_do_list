<?php

include "DAO.class.php";

class UserDAO extends DAO {

    protected function getSelectStatement()
    {
        return "SELECT * FROM users";
    }

    protected function getSpecificSelectStatement($userID)
    {
        return "SELECT * FROM users WHERE user_id=" . $userID;
    }

    protected function getInsertStatement($userObj)
    {
        if (is_a($userObj, 'User')){
            return 'INSERT INTO users (username,password) VALUES ("' . $messageObj->getUsername() . '","' . $messageObj->getPassword() . '")';
        } else {
            throw new Exception('Is not a user object');
        }
    }
}