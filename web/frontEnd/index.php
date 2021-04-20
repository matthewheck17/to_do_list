<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="utf-8"/>
<title>To-Do List</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/showForm.js"></script>
<script type="text/javascript" src="js/formResults.js"></script>

</head>
<body>

<div id="myDIV" class="header">
  <h1>To-Do List</h1>
  <!-- TO-DO: Create ajax/jquery request that correlates with the DAO -->
<form method="post" action="" id="formElement" style="display: none">
  <input type="text" id="myInput" name="inputbox" placeholder="Title">
  <input type="text" id="myDesc" placeholder="Description">
  <span onclick="formResults(this.form)" class="addBtn">Add</span>
</form>

</div>
<button type="button" name="button" onclick="showForm()" class="showBtn">Add Task</button>

<ul id="myUL">
  <?php
  $baseUrl = 'http://127.0.0.1/to_do_list/web/backEnd/';
  $jsondata = file_get_contents($baseUrl . 'tasks/');

  $data = json_decode($jsondata, true);
  foreach ($data AS $d) {
    // If the task is marked completed, strike through the element
    if ($d['completed'] === '0') {
      echo "<li><strike><a href='" . $baseUrl . "tasks/?id=" . $d['task_id'] . "'><b><span class='title'>" . $d['title'] . "</span></b> " . "<p class='content'>" . $d['content'] .'</p></a></strike><button onclick="" class="editTask">Edit</button></li>';
    } else {
      echo "<li><a href='" . $baseUrl . "tasks/?id=" . $d['task_id'] . "'><b><span class='title'>" . $d['title'] . "</span></b> " . "<p class='content'>" . $d['content'] .'</p></a><button onclick="" class="editTask">Edit</button></li>';
    }
  }
  ?>
</ul>
</body>

</body>
</html>
