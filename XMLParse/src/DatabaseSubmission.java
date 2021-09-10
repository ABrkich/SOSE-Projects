import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class DatabaseSubmission {
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

            DomParser DP = new DomParser();
            DP.authorId = 0;
            DP.did = 0;
            DP.authors = new HashMap<String,Integer>();
            List<Article> articles = DP.getArticles();
            List<Inproceedings> inproceedings = DP.getInproceedings();
            List<Proceedings> proceedings = DP.getProceedings();

            Statement state = conn.createStatement();

            for (Object auth : DP.authors.keySet()){
                String aid = DP.authors.get(auth).toString();

                String authSql = "insert into CS7340_lab1.authors values ('" + aid + "','" + auth + "')";
                System.out.println(authSql);
                state.execute(authSql);
            }

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


            /*for(Employee e: employees){
                String sql = "insert into CS7340_lab1.employees values ('" + e.id + "','" + e.firstName + "','" + e.lastName + "','" + e.location + "')";
                state.execute(sql);
            }*/

            /*String select_sql = "select * from employees";
            ResultSet rs = state.executeQuery(select_sql);
            while (rs.next()) {
                String em_id = rs.getString("employee_id");
                String em_fname = rs.getString("employee_fname");
                String em_lname = rs.getString("employee_lname");
                String em_location = rs.getString("employee_location");
                System.out.println("ID: " + em_id + " First: " + em_fname + " Last: " + em_lname + " Location: " + em_location);
            }*/



        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
