# SOSE-Projects
Service Oriented Software Engineering Projects

# Database Setup

There is a mysqldump "cs7340_lab1_dbschema" that contains all of the information of all of the papers from "dblp-soc-papers.xml

To import the mysqldump open up MySql workbench and select the desired MySql Connection. Then click on the administration tab on the Navigator. Under management select the option for "Data Import/Restore". From there navigate to were you saved the "cs7340_lab1_dbschema" select it. Insure that all 3 schema objects are selected then press start import.

The Schema should now be properly imported. To insure this type a test query such as "SELECT * FROM papers WHERE bookt_tile = 'ICWS' and year = 2005;"

If for some reason the Schema or the Data fail to load here is the MySQL to create the tables:

CREATE TABLE papers ( paper_id int, title varchar(255) default NULL, book_title varchar(255) default NULL, pages varchar(255) default NULL, year int default NULL, url varchar(255) default NULL, ee varchar(255) default NULL, publisher varchar(255) default NULL, journal varchar(255) default NULL, volume int default NULL, number int default NULL, crossref varchar(255) default NULL, isbn varchar(255) default NULL, primary KEY(paper_id) );

CREATE TABLE authors ( author_id int, author_name varchar(255) default NULL, primary KEY(author_id) );

CREATE TABLE PapersToAuthors( paper_id int, author_id int, foreign KEY(paper_id) REFERENCES papers(paper_id), foreign KEY(author_id) REFERENCES authors(author_id) );

After creating those three tables you can run DatabaseSubmission.java file located in the "XMLParser/src" sub folder. 

**As a Note there are temp usernames and passwords saved for the purposed of testing located on Line 16 of DatabaseSubmission.java and Line 146 of DatabaseQueries.java which may need to be changed for your connection**

# Running the GUI

The GUI is located in the Java Class Gui.java. Running this will open a GUI with a large blank white space. At the top there are two menus. The left most menu is the Query menu which has all of the Queries for a given mode. Selecting a query will create a form on the bottom to fillout with the appropriate information. Clicking the send button will take the information from the form and send it to the selected query function which will call the SQL server. The output from the query will be posted on the GUI's large white space. 

In the mode menu you can freely change between SQL and XQuery modes. The mode chosen will change the options in the query menu accordingly.

**Note for the XQuery portion only 2 of the queries take an input. Those have a form that can be filled out for that information. The other two just dump the requested informtion to the Large white area**




