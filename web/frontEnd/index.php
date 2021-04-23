<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8"/>
<title>To-Do List</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/functions.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

<div id="myDIV" class="header">
  <h1>To-Do List</h1>
<form method="post" action="" id="formElement" style="display: none">
  <input type="text" id="myInput" name="title" placeholder="Title">
  <input type="text" id="myDesc" name="description" placeholder="Description">
  <input type="button" id="addBtn" class="addBtn" name="button" value="Add" onClick="">
</form>

</div>
<button type="button" name="button" onclick="showForm()" class="showBtn">Add Task</button>
<button type="button" name="button" onclick="" class="refreshBtn">Refresh</button>
<div id="results">
<table id="myTable">
  <tr>
    <th>Task Title</th>
    <th>Task Description</th>
  </tr>
  <?php
  $baseUrl = 'http://127.0.0.1/to_do_list/web/backEnd/';
  $jsondata = file_get_contents($baseUrl . 'tasks/');

  $data = json_decode($jsondata, true);
  foreach ($data AS $d) {
    $taskID = $d['task_id'];
    // If the task is marked completed, strike through the element
    if ($d['completed'] === '1') {
      echo "<tr><strike><td><a href='" . $baseUrl . "tasks/?id=" . $taskID . "'><b><span class='title'>" . $d['title'] . "</span></td><td></b> " . "<p class='content'>" . $d['content'] .'</p><button id="deleteTask" onclick="deleteTask(' . $taskID . ')">Delete</button></td></strike></tr></a>';
    } else {
      echo "<tr><td><a href='" . $baseUrl . "tasks/?id=" . $taskID . "'><span class='title'>" . $d['title'] . "</span></td><td> " . "<p class='content'>" . $d['content'] .'</p><button id="deleteTask" onclick="deleteTask(' . $taskID . ')">Delete</button></td></tr></a>';
      echo "<button id='deleteTask' onclick='deleteTask(" . $taskID . ")'>Delete</button>";
    }
  }
  ?>
</table>
</body>
<script>
$("#deleteTask").click(function(){
  $("#results").load(window.location.href + " #results" );
});

$(document).ready(function() {
  $(".refreshBtn").click(function(){
    $("#results").load(window.location.href + " #results" );
  });
});
</script>
</body>
</html>
