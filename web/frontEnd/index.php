<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8"/>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/showForm.js"></script>
</head>
<body>

<div id="myDIV" class="header">
  <h1>To-Do List</h1>
<form method="post" action="backEnd/services/createTask.php" id="formElement" style="display: none">
  <input type="text" id="myInput" placeholder="Title">
  <input type="text" id="myDesc" placeholder="Description">
  <span onclick="" class="addBtn">Add</span>
</form>

</div>
<button type="button" name="button" onclick="showForm()" class="showBtn">Add Task</button>

<ul id="myUL">
  <?php
  $jsondata = file_get_contents('http://127.0.0.1/to_do_list/web/backEnd/tasks/');

  $data = json_decode($jsondata, true);
  foreach ($data AS $d) {
    echo "<li><a href='http://127.0.0.1/to_do_list/web/backEnd/tasks/?id=" . $d['task_id'] . "'><b><span class='title'>" . $d['title'] . "</span></b> " . "<p class='content'>" . $d['content'] ."</p>" . '</a></li>';
  }
  ?>
</ul>
</body>

</body>
</html>
