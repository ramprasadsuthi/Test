package lab;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MySQLConnectionExample {
    public static void main(String[] args) {
        // Database URL and credentials
        String jdbcUrl = "jdbc:mysql://localhost:3306/sample_db";
        String username = "root"; // Replace with your MySQL username
        String password = "1234"; // Replace with your MySQL password

        // SQL query to retrieve data
        String sql = "SELECT * FROM employees";

        // Load MySQL JDBC Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Connect to the database and execute the query
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            // Process the result set
            System.out.println("ID | First Name | Last Name | Email");
            System.out.println("------------------------------------");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String email = resultSet.getString("email");
                //System.out.printf("%d | %s | %s | %s%n", id, firstName, lastName, email);
                System.out.println(id+" | "+firstName+" | "+lastName+" | "+email);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

