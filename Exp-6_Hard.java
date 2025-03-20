import java.sql.*;
import java.util.Scanner;

class Student {
    int studentID;
    String name, department;
    int marks;
}

class StudentController {
    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "your_username", "your_password");

    void addStudent(Student s) throws SQLException {
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Student VALUES (?, ?, ?, ?)");
        stmt.setInt(1, s.studentID);
        stmt.setString(2, s.name);
        stmt.setString(3, s.department);
        stmt.setInt(4, s.marks);
        stmt.executeUpdate();
    }

    void displayStudents() throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM Student");
        while (rs.next()) {
            System.out.println(rs.getInt("StudentID") + " " + rs.getString("Name") + " " + rs.getString("Department") + " " + rs.getInt("Marks"));
        }
    }
}

public class HardLevelExample {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            switch (scanner.nextInt()) {
                case 1 -> {
                    Student s = new Student();
                    s.studentID = scanner.nextInt();
                    s.name = scanner.next();
                    s.department = scanner.next();
                    s.marks = scanner.nextInt();
                    controller.addStudent(s);
                }
                case 2 -> controller.displayStudents();
                case 3 -> System.exit(0);
            }
        }
    }
}
