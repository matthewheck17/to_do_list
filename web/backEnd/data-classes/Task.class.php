<?php 

class Task {
    private $task_id;
    private $title;
    private $content;
    private $completed;

    public function __construct($title, $content) {
        $this->title = $title;
        $this->content = $content;
    }

    public function setTaskID(){
        $this->task_id = $task_id;
    }

    public function getTaskID(){
        return $this->task_id;
    }

    public function getTitle(){
        return $this->title;
    }

    public function getContent(){
        return $this->content;
    }

    public function getCompleted(){
        return $this->completed;
    }
}