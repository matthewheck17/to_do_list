class Task {
  constructor(id, title, content, completed) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.completed = completed
  }

  toHTML() {
    return '<tr class="task"><td class="tableRow">' + this.title + '</td><td id="content">' + this.content + '</td><td class="buttons"><button type="button" name="button" onclick="deleteTask('+this.id+')" class="deleteBtn" id="deleteBtn"><i class="fas fa-trash-alt"></i></button><button id="'+this.id+'-complete" type="button" name="button" class="completeBtn"><i class="fas fa-check"></i></button><button type="button" name="button" onclick="" class="editBtn"><i class="fas fa-edit"></i></button></td></tr>';
      
        //still need to look into these below
        //document.getElementById("myTable").innerHTML += '<input type="text" name="editTitle" id="editTitle" value="" placeholder="'+o.title+'"><input type="text" name="editContent" id="editContent" placeholder="'+o.content+'" value=""><button type="button" name="button" onclick="editForm('+o.task_id+')" class="submitBtn">Submit</button>';
        //document.getElementById("myTable").innerHTML += '<i class="fas fa-caret-square-down" id="actions">More actions</i>'
  }
}

//let task = new Task(null,'testtask','teasting','0');

window.addEventListener('load', (event) => {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "http://127.0.0.1:8080/to_do_list/web/backEnd/tasks/", true);

  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        populateTasks(JSON.parse(xhr.responseText));
      } else {
        console.error("error");
      }
    }
  };

  xhr.send();
});

function populateTasks (json) {
  let htmlToAdd = "";
  json.forEach(element => {
    task = new Task (element.task_id, element.title, element.content, element.completed);
    document.getElementById("myTable").innerHTML += task.toHTML();
    document.getElementById(element.task_id + "-complete").addEventListener("click", function(){ completeTask(task); });
  });
}


const baseUrl =  "http://localhost:8080/to_do_list/web/backEnd/tasks/"


function deleteTask(id) {
  let xhr = new XMLHttpRequest();
  let url = baseUrl + "?id=" + id;

  // Sets the request type to DELETE
  xhr.open("DELETE", url, true);

  xhr.send();
  document.getElementById("myTable").innerHTML = "";
  dispatchEvent(new Event('load'));
}

function completeTask(task) {
  task.completed = task.completed=="1" ? "0" : "1";
  console.log(task);
  let xhr = new XMLHttpRequest();
  let url = baseUrl + "?id=" + task.id;

  // Sets the request type to DELETE
  xhr.open("PUT", url, true);

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Sends the data
  var data = JSON.stringify({ "title": task.title, "content": task.content, "completed": task.completed });
  xhr.send(data);
}

function reloadTasks() {
  dispatchEvent(new Event('load'));
}


/*
// Function that toggles/hides the form
function showForm(id) {
  x =  document.getElementById(id);
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}



function editForm(id) {
  // Specify the URL
  let xhr = new XMLHttpRequest();
  let url = baseUrl + "?id=" + id;
  let title = document.getElementById('editTitle');
  let content = document.getElementById('editContent');

  // Sets the request type to DELETE
  xhr.open("PUT", url, true);

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Converting JSON data to string
  var data = JSON.stringify({ "title": title.value, "content": content.value, "completed": "0" });

  // Sends the data
  xhr.send(data);
}
function completeTask(title, content) {
  // Specify the URL
  let xhr = new XMLHttpRequest();
  let url = baseUrl + "?id=" + id;

  // Sets the request type to DELETE
  xhr.open("PUT", url, true);

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Converting JSON data to string
  var data = JSON.stringify({ "title": title.value, "content": content.value, "completed": "1" });

  // Sends the data
  xhr.send(data);
}

window.onload = function() {
  document.getElementById("addBtn").onclick = function fun() {
    // Get the two form elements from the index.php page
    let title = document.getElementById('myInput');
    let description = document.getElementById('myDesc');

    // Specify the URL
    let xhr = new XMLHttpRequest();

    // Sets the request type to POST
    xhr.open("POST", baseUrl, true);

    // Set the content type to JSON so the DAO can access it and add it to the database
    xhr.setRequestHeader("Content-Type", "application/json");

    // If the request is successful, clear the inputted text.
    xhr.onreadystatechange = function() {
      if (this.readyState === XMLHttpRequest.DONE && this.status === 200) {
        title.value = '';
        description.value = '';
      }
    }

    // Converting JSON data to string
    var data = JSON.stringify({ "title": title.value, "content": description.value });

    // Sends the data
    xhr.send(data);
  }
  function getJSON(url, callback) {

    var xhr = new XMLHttpRequest();
    xhr.open('GET', url, true);
    xhr.responseType = 'json';

    xhr.onload = function() {

      var status = xhr.status;

      if (status == 200) {
        callback(null, xhr.response);
      } else {
        callback(status);
      }
    };

    xhr.send();
  };

  getJSON(baseUrl,  function(err, data) {
    if (err != null) {
      console.error(err);
    } else {
      data.forEach(function(o) {
        // Ternary operator that strikes through completed tasks
        document.getElementById("myTable").innerHTML += '<tr id="title"><td id="tableRow">' + o.title + '</td><td id="content">' + o.content + '</td><button type="button" name="button" onclick="deleteTask('+o.task_id+')" class="deleteBtn" id="deleteBtn"><i class="fas fa-trash-alt"></i></button><button type="button" name="button" onclick="completeTask('+ o.title +','+ o.content +')" class="completeBtn"><i class="fas fa-check"></i></button><button type="button" name="button" onclick="" class="editBtn"><i class="fas fa-edit"></i></button></tr>';
        document.getElementById("myTable").innerHTML += '<input type="text" name="editTitle" id="editTitle" value="" placeholder="'+o.title+'"><input type="text" name="editContent" id="editContent" placeholder="'+o.content+'" value=""><button type="button" name="button" onclick="editForm('+o.task_id+')" class="submitBtn">Submit</button>';
        document.getElementById("myTable").innerHTML += '<i class="fas fa-caret-square-down" id="actions">More actions</i>'
      });

    }
  });
}
*/