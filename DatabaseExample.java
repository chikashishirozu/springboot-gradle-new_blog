import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseExample {

    public static void main(String[] args) {
        String jdbcUrl = "jdbc:h2:tcp://192.168.3.100:9092/testdb";
        String username = "sa";
        String password = "";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Connected to the database!");

            // データベース操作の例
            String sql = "SELECT * FROM blog";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    System.out.println("ID: " + resultSet.getLong("id"));
                    System.out.println("Title: " + resultSet.getString("title"));
                    System.out.println("Content: " + resultSet.getString("content"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

