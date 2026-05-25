import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ConnectionPoolTest {
    @Test
    void testHikariCP() {
        HikariConfig config = new HikariConfig();
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/bioskop");
        config.setUsername("root");
        config.setPassword("farhan137");

        config.setMaximumPoolSize(10);
        config.setMinimumIdle(5);
        config.setIdleTimeout(60_000);
        config.setMaxLifetime(10 * 60_000);

        try {
            HikariDataSource dataSource = new HikariDataSource(config);
            Connection con = dataSource.getConnection();
            con.close();
            dataSource.close();
        } catch (SQLException e) {
            Assertions.fail(e);
        }
    }

    @Test
    void testUtil() throws SQLException {
        Connection con = util.ConnectionUtil.getDataSource().getConnection();
    }
}
