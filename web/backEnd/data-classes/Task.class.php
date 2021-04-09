<?php 

class Task {
    private $task_id;
    private $title;
    private $content;
    private $completed;

    public function __construct($task_id, $title, $content, $completed) {
        $this->task_id = $task_id;
        $this->title = $title;
        $this->content = $content;
        $this->completed = $completed;
    }

    public function getID(){
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