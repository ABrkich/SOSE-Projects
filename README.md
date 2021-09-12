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

**As a Note there are temp usernames and passwords saved for the purposed of testing located on Line 16 of DatabaseSubmission.java and Line 146 of DatabaseQueries.java which may need to be changed for your connection

# Java Querying the MySql Database

As mentioned above these 4 queries are done in the DatabaseQueries.java file.

Each of these Queries uses it's own dedicated funtion which takes in the requesit variables and outputs the SQL response to the java terminal. 

The 4 functions are:
	getCoauthors(String author, Connection conn)
	getPaperMetadata(String title, Connection conn)
	getJournalMetadata(String name, String year, String volume, String number, Connection conn)
	getConferenceMetadata(String conf, String year, Connection conn)

An example of each of these functions running are on lines 153-156 of DatabaseQueries.java and can be amended to whatever Author/Journal paper/Conference or commented out as needs require.


# XQuerying the XML
put things here

The 4 Xqueries can be found in publication.xqy and are called by running XQueryTester.java

Running XQueryTester.java only runs one specific query, and the other 3 are commented out. You will need to comment out the query you ran and uncomment out the next one you want to run.

For some of the queries, we look for $x in dblp/article or dblp/inproceedings. This will return the result for the query in that subsection of the dblp.
If you want to run the query on all publications (articles AND inproceedings AND etc) simply just change the route on the query in the for statement and rerun it
ex: for $x in doc("dblp-soc-papers.xml")/dblp/inproceedings
Change to /dblp/article to run on articles instead of inproceedings
etc.

Query 1:
We did not find any titles that strictly had the letters "SOSE" in them, so we ran our subject queries on "Software Engineering" instead. 
You can easily change this 
ex: where fn:contains($x/title, 'Software Engineering')
Change 'Softare Engineering' to whatever you want to compare

Query 2: Per seen above, to view all articles published by Jia Zhang in 2018, change the article route to inproceedings or vice versa.

Query 3: We did not find any authors with > 10 publications, however this may be due to our query being faulty.

Query 4: We declared a global variable $papername, you may change this to whatever paper name you want to run the query on (case sensitve, must be exact)




