// Function that toggles/hides the form
function showForm() {
  x =  document.getElementById('formElement');
  if (x.style.display === "none") {
    x.style.display = "block";
  } else {
    x.style.display = "none";
  }
}

function sendJSON() {

  // Get the two form elements from the index.php page
  let title = document.getElementById('myInput');
  let description = document.getElementById('myDesc');

  // Specify the URL
  let xhr = new XMLHttpRequest();
  let url = "http://127.0.0.1/to_do_list/web/backEnd/tasks/";

  // Sets the request type to POST
  xhr.open("POST", url, true);

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
