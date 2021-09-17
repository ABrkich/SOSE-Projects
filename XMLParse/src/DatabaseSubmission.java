import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseSubmission {
    public static void main(String[] args) {

        // https://docs.oracle.com/javase/8/docs/api/java/sql/package-summary.html#package.description
        // auto java.sql.Driver discovery -- no longer need to load a java.sql.Driver class via Class.forName

        // register JDBC driver, optional since java 1.6

        // auto close connection
        try (
                Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/CS7340_lab1", "root", "Chas3755")) {

            if (conn != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            DomParser DP = new DomParser();
            DP.authorId = 0;
            DP.did = 0;
            DP.authors = new HashMap<String,Integer>();
            List<Article> articles = DP.getArticles();
            List<Inproceedings> inproceedings = DP.getInproceedings();
            List<Proceedings> proceedings = DP.getProceedings();

            Statement state = conn.createStatement();

            //Submit Authors to the Author Table
            for (Object auth : DP.authors.keySet()){
                String aid = DP.authors.get(auth).toString();

                String authSql = "insert into CS7340_lab1.authors values ('" + aid + "','" + auth + "')";
                System.out.println(authSql);
                state.execute(authSql);
            }

            //Submit Publications to the papers table
            for (Article a : articles) {
                String sql = "insert into CS7340_lab1.papers values ('" + a.did + "','" + a.title + "'," + "NULL" + ",'" + a.pages + "','" + a.year + "','" + a.url + "','" + a.ee.toString() + "'," + "NULL" + ",'" + a.journal + "','" + a.volume + "','" + a.number + "'," + "NULL" + "," + "NULL)";
                System.out.println(sql);
                state.execute(sql);
                for (Author auth : a.authors){
                    String pap2auth = "insert into CS7340_lab1.paperstoauthors values ('" + a.did + "','" + auth.aid + "')";
                    System.out.println(pap2auth);
                    state.execute(pap2auth);
                }
            }

            for (Inproceedings i : inproceedings) {
                String sql = "insert into CS7340_lab1.papers values ('" + i.did + "','" + i.title + "','" + i.booktitle + "','" + i.pages + "','" + i.year + "','" + i.url + "','" + i.ee.toString() + "'," + "NULL" + "," + "NULL" + "," + "NULL" + "," + "NULL" + ",'" + i.crossRef + "'," + "NULL)";
                System.out.println(sql);
                state.execute(sql);
                for (Author auth : i.authors){
                    String pap2auth = "insert into CS7340_lab1.paperstoauthors values ('" + i.did + "','" + auth.aid + "')";
                    System.out.println(pap2auth);
                    state.execute(pap2auth);
                }
            }

            for (Proceedings p : proceedings) {
                String sql = "insert into CS7340_lab1.papers values ('" + p.did + "','" + p.title + "','" + p.booktitle + "'," + "NULL" + ",'" + p.year + "','" + p.url + "'," + "NULL" + ",'" + p.publisher + "'," + "NULL" + "," + "NULL" + "," + "NULL" + "," + "NULL" + ",'" + p.isbn + "')";
                System.out.println(sql);
                state.execute(sql);
            }

            System.out.println(DP.authorId);

        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
