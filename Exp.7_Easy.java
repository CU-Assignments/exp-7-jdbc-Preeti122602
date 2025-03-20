import java.sql.*;

public class EasyLevelExample {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT EmpID, Name, Salary FROM Employee")) {

            while (rs.next()) {
                System.out.println(rs.getInt("EmpID") + " " + rs.getString("Name") + " " + rs.getDouble("Salary"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
