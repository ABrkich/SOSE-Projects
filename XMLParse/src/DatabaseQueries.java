import javax.xml.crypto.Data;
import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseQueries {

    public static String getCoauthors(String author) throws SQLException {

        DatabaseQueries dbq = new DatabaseQueries();

        String outstring = "";

        String select_sql = "Select * from authors where author_id in (Select distinct author_id from paperstoauthors where paper_id in (Select paper_id from paperstoauthors pa left join authors a on a.author_id = pa.author_id where a.author_name = '" + author + "')) AND author_name != '" + author + "';";

        Statement state = dbq.conn.createStatement();
        ResultSet rs = state.executeQuery(select_sql);
        while (rs.next()) {
            String coauthor = rs.getString("author_name");
            outstring+=  ("Coauthor: " + coauthor +"\n");
        }

        return outstring;
    }

    public static String getPaperMetadata(String title) throws SQLException {
        DatabaseQueries dbq = new DatabaseQueries();
        String outstring = "";
        String select_sql = "select * from papers right join paperstoauthors on papers.paper_id = paperstoauthors.paper_id join authors on authors.author_id = paperstoauthors.author_id where title = \""+ title + "\";";
        Statement state = dbq.conn.createStatement();
        ResultSet rs = state.executeQuery(select_sql);

        String pages = "";
        String year = "";
        String url = "";
        String ee = "";
        String journal = "";
        String conf = "";
        String volume = "";
        String number = "";
        String authors = "";

        while (rs.next()) {
            title = rs.getString("title");
            pages = rs.getString("pages");
            year = rs.getString("year");
            url = rs.getString("url");
            ee = rs.getString("ee");
            conf = rs.getString("book_title");
            journal = rs.getString("journal");
            volume = rs.getString("volume");
            number = rs.getString("number");
            authors += (rs.getString("author_name") + ", ");
        }
        if(conf == null) {
            outstring += ("Title: " + title + "\n");
            if(authors.length() > 0) {
                outstring += ("Authors: " + authors.substring(0, authors.length() - 2) + "\n");
            }
            else{
                outstring += ("Authors: " + authors + "\n");
            }
            outstring += ("Journal: " + journal + "\n");
            outstring += ("Year: " + year + "\n");
            outstring += ("Volume: " + volume + "\n");
            outstring += ("Number: " + number + "\n");
            outstring += ("Pages: " + pages + "\n");
            outstring += ("Url: " + url + "\n");
            outstring += ("EEs: " + ee + "\n");
        }

        else if(journal == null){
            outstring += ("Title: " + title + "\n");

            if(authors.length() > 0) {
                outstring += ("Authors: " + authors.substring(0, authors.length() - 2) + "\n");
            }
            else{
                outstring += ("Authors: " + authors + "\n");
            }
            outstring += ("Conference: " + conf + "\n");
            outstring += ("Year: " + year + "\n");
            outstring += ("Pages: " + pages + "\n");
            outstring += ("Url: " + url + "\n");
            outstring += ("EEs: " + ee + "\n");

        }

        return outstring;
    }


    public static String getJournalMetadata(String name, String volume,String number) throws SQLException {
        DatabaseQueries dbq = new DatabaseQueries();
        String outstring = "";
        String select_sql = "select * from papers where journal = '" + name + "' and  volume = "+ volume +" and number =" + number +";";
        Statement state = dbq.conn.createStatement();
        ResultSet rs = state.executeQuery(select_sql);

        while (rs.next()) {
            outstring += "_________________________________________________________\n";
            String title = rs.getString("title");
            outstring += getPaperMetadata(title);
        }

        return outstring;
    }


    public static String getConferenceMetadata(String conf, String year) throws SQLException {
        DatabaseQueries dbq = new DatabaseQueries();
        String outstring = "";
        String select_sql = "select title from papers where book_title = '" + conf + "' and year = " + year + " and pages != 'null';";
        Statement state = dbq.conn.createStatement();
        ResultSet rs = state.executeQuery(select_sql);

        while (rs.next()) {
            outstring += "_________________________________________________________\n";
            String title = rs.getString("title");
            outstring += getPaperMetadata(title);
        }

        return outstring;
        }
        public static void main(String[] args) throws SQLException {



    }

    static Connection conn;

    static {
        try {
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/CS7340_lab1", "root", "Chas3755");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

