<!DOCTYPE html>
<html>
<head>
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta charset="utf-8"/>
  <title>To-Do List</title>
  <link rel="stylesheet" href="css/style.css">
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.1/css/all.css" crossorigin="anonymous">
  <script type="text/javascript" src="js/functions.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</head>
<body>

  <div id="myDIV" class="header">
    <h1>To-Do List</h1>
    <form method="post" id="formElement" style="display: none">
      <input type="text" id="myInput" name="title" placeholder="Title">
      <input type="text" id="myDesc" name="description" placeholder="Description">
      <input type="button" id="addBtn" class="addBtn" name="button" value="Add" onClick="">
    </form>

  </div>
  <button type="button" name="button" onclick="showForm('formElement')" class="showBtn">Add Task</button>
  <button type="button" name="button" onclick="" class="refreshBtn">Refresh</button>
  <div id="results">
    <table id="myTable" class="myClass">
      <tr>
        <th>Task Title</th>
        <th>Task Description</th>
      </tr>
      <!-- Populated using the window.onload method -->
    </table>
  </div>
  </body>
  </html>
