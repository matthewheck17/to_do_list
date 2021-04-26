class Task {
  constructor(id, title, content, completed) {
    this.id = id;
    this.title = title;
    this.content = content;
    this.completed = completed
  }

  toHTML() {
    // If the task is completed, display with a line through it
    if (task.completed == "1") {
      return `<tr class="task">
                <td id="`+this.id+`-title" class="tableRow" style="text-decoration: line-through;" onclick=startEditingTitle(`+this.id+`)>` + this.title + `</td>
                <td id="`+this.id+`-title-editor" class="tableRow" style="display:none;">
                  <input type="text" id="`+this.id+`-edit-title" class="edit-title" name="`+this.id+`-edit-title" value="` + this.title + `">
                  <button type="button" name="`+this.id+`-edit-title-button" onclick="updateTitle(` + this.id +`)" class="editBtn">
                    <i class="fas fa-check"></i>
                  </button>
                </td>
                <td id="`+this.id+`-content" style="text-decoration: line-through;" onclick=startEditingContent(`+this.id+`)>` + this.content + `</td>
                <td id="`+this.id+`-content-editor" class="tableRow" style="display:none;">
                  <input type="text" id="`+this.id+`-edit-content" class="edit-content" name="`+this.id+`-edit-content" value="` + this.content + `">
                  <button type="button" name="`+this.id+`-edit-content-button" onclick="updateContent(` + this.id +`)" class="editBtn">
                    <i class="fas fa-check"></i>
                  </button>
                </td>
                <td id="`+this.id+`-completed" style="display:none;" class="tableRow">` + this.completed + `</td>
                <td class="buttons">
                  <button id="`+this.id+`-delete" type="button" name="button" class="deleteBtn" id="deleteBtn" onclick=deleteTask(` + this.id +`)>
                    <i class="fas fa-trash-alt"></i>
                  </button>
                  <button id="`+this.id+`-complete" type="button" name="button" class="completeBtn" onclick=completeTask(` + this.id +`)>
                    <i class="fas fa-check"></i>
                  </button>
                </td>
              </tr>`;
    } else {
      return `<tr class="task">
                <td id="`+this.id+`-title" class="tableRow" onclick=startEditingTitle(`+this.id+`)>` + this.title + `</td>
                <td id="`+this.id+`-title-editor" class="tableRow" style="display:none;">
                  <input type="text" id="`+this.id+`-edit-title" class="edit-title" name="`+this.id+`-edit-title" value="` + this.title + `">
                  <button type="button" name="`+this.id+`-edit-title-button" onclick="updateTitle(` + this.id +`)" class="editBtn">
                    <i class="fas fa-check"></i>
                  </button>
                </td>
                <td id="`+this.id+`-content" onclick=startEditingContent(`+this.id+`)>` + this.content + `</td>
                <td id="`+this.id+`-content-editor" class="tableRow" style="display:none;">
                  <input type="text" id="`+this.id+`-edit-content" class="edit-content" name="`+this.id+`-edit-content" value="` + this.content + `">
                  <button type="button" name="`+this.id+`-edit-content-button" onclick="updateContent(` + this.id +`)" class="editBtn">
                    <i class="fas fa-check"></i>
                  </button>
                </td>
                <td id="`+this.id+`-completed" style="display:none;" class="tableRow">` + this.completed + `</td>
                <td class="buttons">
                  <button id="`+this.id+`-delete" type="button" name="button" class="deleteBtn" id="deleteBtn" onclick=deleteTask(` + this.id +`)>
                    <i class="fas fa-trash-alt"></i>
                  </button>
                  <button id="`+this.id+`-complete" type="button" name="button" class="completeBtn" onclick=completeTask(` + this.id +`)>
                    <i class="fas fa-check"></i>
                  </button>
                </td>
              </tr>`;
    }
  }
}

var filter = "all";

// Function for displaying all of the tasks regardless of filter
function showAll() {
  filter = "all";
  reloadTasks();
}

// Function for displaying all the tasks with the filter of complete
function showComplete() {
  filter = "complete";
  reloadTasks();
}

// Function for displaying all the tasks with the filter of incomplete
function showIncomplete() {
  filter = "incomplete";
  reloadTasks();
}

function createTaskFromID(id) {
  let task = new Task (id, document.getElementById(id + "-title").textContent,document.getElementById(id + "-content").textContent,document.getElementById(id + "-completed").textContent);
  return task;
}

window.addEventListener('load', (event) => {
  let xhr = new XMLHttpRequest();
  xhr.open("GET", "http://localhost:8080/to_do_list/web/backEnd/tasks/", true);

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

// Populates the tasks on the front page according to the filter selected
function populateTasks (json) {
  let htmlToAdd = "";
  json.forEach(element => {
    task = new Task (element.task_id, element.title, element.content, element.completed);
    switch(filter) {
      case "all":
        document.getElementById("myTable").innerHTML += task.toHTML();
        break;
      case "complete":
        if (task.completed === "1"){
          document.getElementById("myTable").innerHTML += task.toHTML();
        }
        break;
      case "incomplete":
          if (task.completed === "0"){
            document.getElementById("myTable").innerHTML += task.toHTML();
          }
          break;
      default:
        document.getElementById("myTable").innerHTML += task.toHTML();
    }
  });
}

const BASEURL =  "/to_do_list/web/backEnd/tasks/"

// Function for creating tasks based on the task object
function createTask(task) {
  let xhr = new XMLHttpRequest();

  xhr.open("POST", BASEURL, true);

  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        reloadTasks();
      } else {
        console.error(xhrStatus + "code");
      }
    }
  };

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Sends the data
  var data = JSON.stringify({ "title": task.title, "content": task.content});
  xhr.send(data);
}

// Function for deleting tasks based on the ID
function deleteTask(id) {
  let xhr = new XMLHttpRequest();
  let url = BASEURL + "?id=" + id;

  // Sets the request type to DELETE
  xhr.open("DELETE", url, true);

  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        reloadTasks();
      } else {
        console.error(xhrStatus + "code");
      }
    }
  };

  xhr.send();
}

function updateTask(task) {
  let xhr = new XMLHttpRequest();
  let url = BASEURL + "?id=" + task.id;

  xhr.open("PUT", url, true);

  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        reloadTasks();
      } else {
        console.error(xhrStatus + "code");
      }
    }
  };

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Sends the data
  var data = JSON.stringify({ "title": task.title, "content": task.content, "completed": task.completed });
  xhr.send(data);
}

// Function for completing tasks by ID
function completeTask(id) {
  let task = createTaskFromID(id);
  task.completed = task.completed=="1" ? "0" : "1";
  let xhr = new XMLHttpRequest();
  let url = BASEURL + "?id=" + task.id;

  xhr.open("PUT", url, true);

  xhr.onload = (e) => {
    if (xhr.readyState === 4) {
      if (xhr.status === 200) {
        reloadTasks();
      } else {
        console.error(xhrStatus + "code");
      }
    }
  };

  // Set the content type to JSON so the DAO can access it and add it to the database
  xhr.setRequestHeader("Content-Type", "application/json");

  // Sends the data
  var data = JSON.stringify({ "title": task.title, "content": task.content, "completed": task.completed });
  xhr.send(data);
}

function startEditingTitle(id) {
  document.getElementById(id+"-title").style.display = "none";
  document.getElementById(id+"-title-editor").style.display = "table-cell";
}

function finishEditingTitle(id) {
  document.getElementById(id+"-title").style.display = "table-cell";
  document.getElementById(id+"-title-editor").style.display = "none";
}


function updateTitle(id) {
  let task = createTaskFromID(id);
  task.title = document.getElementById(id + "-edit-title").value;
  updateTask(task);
  finishEditingTitle(id);
}

function startEditingContent(id) {
  document.getElementById(id+"-content").style.display = "none";
  document.getElementById(id+"-content-editor").style.display = "table-cell";
}

function finishEditingContent(id) {
  document.getElementById(id+"-content").style.display = "table-cell";
  document.getElementById(id+"-content-editor").style.display = "none";
}


function updateContent(id) {
  let task = createTaskFromID(id);
  task.content = document.getElementById(id + "-edit-content").value;
  updateTask(task);
  finishEditingContent(id);
}

function showAddTaskForm() {
  document.getElementById("add-task-row").style.display = "table-row";
}

function hideAddTaskForm() {
  document.getElementById("add-task-row").style.display = "none";
}

function addTask() {
  title = document.getElementById("add-title").value;
  content = document.getElementById("add-content").value;
  if (!title || !content){
    if (!title) {
      document.getElementById("add-title").style.border = "solid 1px red";
    } else {
      document.getElementById("add-title").style.border = "none";
    }
    if (!content) {
      document.getElementById("add-content").style.border = "solid 1px red";
    } else {
      document.getElementById("add-content").style.border = "none";
    }
  } else {
    let task = new Task (null,title,content,0);
    hideAddTaskForm();
    createTask(task);

  }

}

// Function for reloading the task population without reloading the entire page
function reloadTasks() {
  setTimeout(function(){},)
  document.getElementById("myTable").innerHTML = `<tr>
                                                    <th>Task Title</th>
                                                    <th>Task Description</th>
                                                  </tr>
                                                  <tr id="add-task-row" style="display:none;">
                                                    <td id="title-add" class="tableRow">
                                                      <input type="text" class="edit-title" id="add-title" name="add-title">
                                                    </td>
                                                    <td id="content-add" class="tableRow">
                                                      <input type="text" class="edit-content" id="add-content" name="add-content">
                                                    </td>
                                                    <td class="tableRow">
                                                      <button type="button" name="finish-add-task-button" onclick="addTask()" class="add-task-btn">
                                                        <i class="fas fa-check"></i>
                                                      </button>
                                                    </td>
                                                  </tr>`;
  dispatchEvent(new Event('load'));
}
