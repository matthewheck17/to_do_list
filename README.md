# to_do_list

Final Project for CIS 440

## Description
The students of this class will cooperate in order to create a fully functional application that allows users to organize “to do” lists. The server side of the application will consist of a series of web services. There will be two separate clients for this system: a web and an android app. 

The apps will create, update, delete, and read “to do” tasks from the server through the web services provided. All users will share the same set of tasks. A task can be marked as completed.

Each student will be graded according to his or her own contribution. Each student must keep track of his or her own contributions and be able to prove them through the commit log on GitHub.

The following HTTP RESTful services must be made available by the server (and nothing more, unless approved by the professor):
-	A GET request to the URL [appdomain]/tasks returns all tasks. 
-	A GET request to the URL [appdomain]/tasks?id=1 returns the task with id 1. The HTTP response 200 (OK) should be returned if the resource exists and 404 otherwise.
-	A POST request to the URL [appdomain]/tasks with a JSON representing the task (without its ID) creates a new task. If the server successfully performs the operation, use the response code 200 (OK) and return a JSON with the newly created task (with its ID) in the body of the message.
-	A PUT request to the URL [appdomain]/tasks?id=1 updates the existing task according to a JSON representing the new state of the task. A successful update should return HTTP response 200.
-	A DELETE request to the URL [appdomain]/tasks?id=1 deletes the task with id 1. A HTTP response 200 should be returned if a task has been deleted and 404 if the file to be deleted was not found.

Concerning the clients:
-	The clients should be able to interact with the server through all of its services.
-	All of the REST services shall be consumed by means of JavaScript requests in the web app.
-	If the specifications are followed correctly, the same web services will provide the same functionality both for the web and mobile app.
-	Users will be able to mark tasks as done and not done. 
-	Users will be able to see a list of tasks not yet done.
-	Users will be able to see a list of tasks done.
-	Users will be able to open/edit/delete a task.

Other requirements:
-	Tasks should have a title, description, and a boolean field “done”.
-	The system must be object oriented.
-	Access to database should be done through a DAO.
-	The system must be thoroughly tested and error/exception free. If something is not working, don’t leave it there to the final user.
-	Data must be fully validated on the server side.
-	Data must be validated where applicable on the client side.
-	Follow coding guidelines and conventions
o	Use adequate naming conventions.
o	Be consistent in your formatting and indentation.
o	Don’t use magic numbers (i.e., non-obvious values that aren’t declared as constants or variables – literal numbers)
-	Include at least two automated tests cases for each web service (see for example “postman”). One must cover a successful operation and another a failure to perform operation. 

All these requirements should be fulfilled in a meaningful way. Ask the professor if you are not sure whether what you have is meaningless or not.

A presentation is due at the end of the project (see syllabus). Any member of a group that does not demonstrate knowledge of the code contributed in his project might have his grade decreased accordingly.
