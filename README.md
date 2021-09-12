# SOSE-Projects
Service Oriented Software Engineering Projects

Make sure to change the password in the "DatabaseSubmission Class"
MySQL Statements to Create the Tables

CREATE TABLE papers
(
	paper_id int,
	title varchar(255) default NULL,
    book_title varchar(255) default NULL,
    pages varchar(255) default NULL,
    year int default NULL,
    url varchar(255) default NULL,
    ee varchar(255) default NULL,
    publisher varchar(255) default NULL,
    journal varchar(255) default NULL,
    volume int default NULL,
    number int default NULL,
    crossref varchar(255) default NULL,
    isbn varchar(255) default NULL,
    primary KEY(paper_id)
);

CREATE TABLE authors
(
	author_id int,
	author_name varchar(255) default NULL,
	primary KEY(author_id)
);

CREATE TABLE PapersToAuthors(
	paper_id int,
    author_id int,
    foreign KEY(paper_id) REFERENCES papers(paper_id),
    foreign KEY(author_id) REFERENCES authors(author_id)
);
