CREATE TABLE tasks (
	task_id int NOT NULL AUTO_INCREMENT UNIQUE PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content VARCHAR(1500) NOT NULL,
    completed BOOL DEFAULT false
);

INSERT INTO tasks (title, content)
VALUES ('Test task','This is a test task');

INSERT INTO tasks (title, content, completed) 
VALUES ('Test completed task','This is a test completed task', true);