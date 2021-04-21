<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8"/>
<title>To-Do List</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/functions.js"></script>
<script type="text/javascript" src="js/formResults.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

<div id="myDIV" class="header">
  <h1>To-Do List</h1>
<form method="post" action="" id="formElement" style="display: none">
  <input type="text" id="myInput" name="title" placeholder="Title">
  <input type="text" id="myDesc" name="description" placeholder="Description">
  <input type="button" id="addBtn" class="addBtn" name="button" value="Add" onClick="sendJSON()">
</form>

</div>
<button type="button" name="button" onclick="showForm()" class="showBtn">Add Task</button>
<div id="results">
<ul id="myUL">
  <?php
  $baseUrl = 'http://127.0.0.1/to_do_list/web/backEnd/';
  $jsondata = file_get_contents($baseUrl . 'tasks/');

  $data = json_decode($jsondata, true);
  foreach ($data AS $d) {
    // If the task is marked completed, strike through the element
    if ($d['completed'] === '1') {
      echo "<li id><strike><a href='" . $baseUrl . "tasks/?id=" . $d['task_id'] . "'><b><span class='title'>" . $d['title'] . "</span></b> " . "<p class='content'>" . $d['content'] .'</p></a></strike><button id="editTask" onclick="editForm()">Edit</button><input type="text" id="editTitle" name="title" placeholder="'. $d['title'] . '"></input><input type="text" id="editDesc" name="Description" placeholder="Description" placeholder="'. $d['content'] . '"></li>';
    } else {
      echo "<li><a href='" . $baseUrl . "tasks/?id=" . $d['task_id'] . "'><b><span class='title'>" . $d['title'] . "</span></b> " . "<p class='content'>" . $d['content'] .'</p></a><button id="editTask" onclick="editForm()"> Edit</button><input type="text" id="editTitle" name="title" placeholder="'. $d['title'] . '"></input><input type="text" id="editDesc" name="Description" placeholder="'. $d['content'] . '"></input></li>';
    }
  }
  ?>
</ul>
</body>
<script>
// Refreshes the results without reloading the page

$(document).ready(function(){
setInterval(function(){
      $("#results").load(window.location.href + " #results" );
}, 10000);
});
</script>
</body>
</html>
