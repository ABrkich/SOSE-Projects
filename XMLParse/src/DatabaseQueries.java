import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseQueries {

    public static void getCoauthors(String author, Connection conn){
        try {
            String select_sql = "Select * from authors where author_id in (Select distinct author_id from paperstoauthors where paper_id in (Select paper_id from paperstoauthors pa left join authors a on a.author_id = pa.author_id where a.author_name = '" + author + "')) AND author_name != '" + author + "';";
            System.out.println(select_sql);
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(select_sql);
            while (rs.next()) {
                String coauthor = rs.getString("author_name");
                System.out.println("Coauthor: " + coauthor);
            }
        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static void getPaperMetadata(String title, Connection conn){
        try {
            String select_sql = "select * from papers right join paperstoauthors on papers.paper_id = paperstoauthors.paper_id join authors on authors.author_id = paperstoauthors.author_id where title = \""+ title + "\";";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(select_sql);

            String pages = "";
            String year = "";
            String url = "";
            String ee = "";
            String journal = "";
            String volume = "";
            String number = "";
            String authors = "";

            while (rs.next()) {
                title = rs.getString("title");
                pages = rs.getString("pages");
                year = rs.getString("year");
                url = rs.getString("url");
                ee = rs.getString("ee");
                journal = rs.getString("journal");
                volume = rs.getString("volume");
                number = rs.getString("number");
                authors += (rs.getString("author_name") + ", ");
            }

            System.out.println("Title: " + title);
            System.out.println("Authors: " + authors.substring(0,authors.length()-1));
            System.out.println("Journal: " + journal);
            System.out.println("Year: " + year);
            System.out.println("Volume: " + volume);
            System.out.println("Number: " + number);
            System.out.println("Pages: " + pages);
            System.out.println("Url: " + url);
            System.out.println("EEs: " + ee);

        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    public static void getJournalMetadata(String name, String year, String volume,String number, Connection conn){
        try {
            String select_sql = "select * from papers where journal = '" + name + "' and year = " + year + " and volume = "+ volume +" and number =" + number +";";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(select_sql);

            while (rs.next()) {
                System.out.println("_________________________________________________________\n");
                String title = rs.getString("title");
                getPaperMetadata(title,conn);
            }

        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static void getConfPaperMetadata(String title, Connection conn){
        try {
            String select_sql = "select * from papers right join paperstoauthors on papers.paper_id = paperstoauthors.paper_id join authors on authors.author_id = paperstoauthors.author_id where title = \""+ title + "\";";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(select_sql);

            String pages = "";
            String year = "";
            String url = "";
            String ee = "";
            String conf = "";
            String authors = "";

            while (rs.next()) {
                title = rs.getString("title");
                pages = rs.getString("pages");
                year = rs.getString("year");
                url = rs.getString("url");
                ee = rs.getString("ee");
                conf = rs.getString("book_title");
                authors += (rs.getString("author_name") + ", ");
            }

            System.out.println("Title: " + title);
            if(authors.length() > 0) {
                System.out.println("Authors: " + authors.substring(0, authors.length() - 2));
            }
            else{
                System.out.println("Authors: " + authors);
            }
            System.out.println("Conference: " + conf);
            System.out.println("Year: " + year);
            System.out.println("Pages: " + pages);
            System.out.println("Url: " + url);
            System.out.println("EEs: " + ee);

        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }

    public static void getConferenceMetadata(String conf, String year, Connection conn){
        try {
            String select_sql = "select * from papers where book_title = '" + conf + "' and year = " + year + ";";
            Statement state = conn.createStatement();
            ResultSet rs = state.executeQuery(select_sql);

            while (rs.next()) {
                System.out.println("_________________________________________________________\n");
                String title = rs.getString("title");
                getConfPaperMetadata(title,conn);
            }

        }catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
    public static void main(String[] args) {

        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional since java 1.6
        /*try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }*/

        // auto close connection
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/CS7340_lab1", "root", "Chas3755")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }
            Statement state = conn.createStatement();
            getCoauthors("Athman Bouguettaya",conn);
            getPaperMetadata("Long-Term QoS-Aware Cloud Service Composition Using Multivariate Time Series Analysis.", conn);
            getJournalMetadata("IEEE Trans. Services Computing", "2016","9", "3", conn);
            getConferenceMetadata("ICWS","2005", conn);


        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

