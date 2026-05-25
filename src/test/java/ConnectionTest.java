import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ConnectionTest {

    @BeforeAll
    static void beforeAll() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testConnection() {
        String jdbcUrl = "jdbc:mysql://localhost:3306/bioskop";
        String username = "root";
        String password = "farhan137";

        try (Connection con = DriverManager.getConnection(jdbcUrl, username, password)) {
            System.out.println("Sukses tehubung ke database");
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }
}
