import java.sql.*;
import java.util.Scanner;

public class MediumLevelExample {
    static Connection conn;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");

        while (true) {
            switch (scanner.nextInt()) {
                case 1 -> createProduct();
                case 2 -> readProducts();
                case 3 -> updateProduct();
                case 4 -> deleteProduct();
                case 5 -> System.exit(0);
            }
        }
    }

    static void createProduct() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)");
        stmt.setString(1, scanner.next());
        stmt.setDouble(2, scanner.nextDouble());
        stmt.setInt(3, scanner.nextInt());
        stmt.executeUpdate();
    }

    static void readProducts() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Product");
        while (rs.next()) {
            System.out.println(rs.getInt("ProductID") + " " + rs.getString("ProductName") + " " + rs.getDouble("Price") + " " + rs.getInt("Quantity"));
        }
    }

    static void updateProduct() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?");
        stmt.setDouble(1, scanner.nextDouble());
        stmt.setInt(2, scanner.nextInt());
        stmt.setInt(3, scanner.nextInt());
        stmt.executeUpdate();
    }

    static void deleteProduct() throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("DELETE FROM Product WHERE ProductID = ?");
        stmt.setInt(1, scanner.nextInt());
        stmt.executeUpdate();
    }
}
