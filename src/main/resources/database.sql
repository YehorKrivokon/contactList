CREATE TABLE users (
  id       INT PRIMARY KEY,
  username VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL

);

CREATE TABLE contacts(
  id       INT PRIMARY KEY,
  contactlogin VARCHAR(255) NOT NULL,
  contactfullname VARCHAR(255) NOT NULL,
  contactphone INTEGER NOT NULL,
  contactdescription TEXT NOT NULL,
  contactimportance TEXT NOT NULL,
  contactstatus TEXT NOT NULL,
  user_id INT NOT NULL,
  FOREIGN KEY (user_id)REFERENCES users(id)
)