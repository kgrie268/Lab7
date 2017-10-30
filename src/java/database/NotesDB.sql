DROP DATABASE if exists NotesDB;
CREATE DATABASE NotesDB;

USE NotesDB;


DROP TABLE Notes;

CREATE TABLE Notes( 
    noteID INT NOT NULL AUTO_INCREMENT,
    dateCreated DATETIME NOT NULL,
    contents NVARCHAR(1000) CHARACTER SET utf8 NOT NULL,
  
    PRIMARY KEY (noteID)
);

INSERT INTO User values('admin', 'password', 'test@test.com', 1, 'Bob', 'Bobberson');
