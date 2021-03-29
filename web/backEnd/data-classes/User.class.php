<?php 

class User {
    private $user_id;
    private $username;
    private $password;

    public function __construct($username, $password) {
        $this->username = $username;
        $this->password = $password;
    }

    public function setUserID($user_id){
        $this->user_id = $user_id;
    }

    public function getUserID(){
        return $this->sent_to;
    }

    public function getUsername(){
        return $this->heading;
    }

    public function getPassword(){
        return $this->sent_from;
    }
}