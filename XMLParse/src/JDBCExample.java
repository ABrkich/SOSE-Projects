import java.sql.*;
import java.util.HashMap;
import java.util.List;

public class JDBCExample {
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

            for (Article a : articles) {
                System.out.println(a);
            }

            Statement state = conn.createStatement();
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