import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

public class StatementTest {
    
    @Test
    void testCreateStatement() throws SQLException {
        Connection con = util.ConnectionUtil.getDataSource().getConnection();
        Statement statement = con.createStatement();

        String query = """
                INSERT INTO users(id, username, password, peran)
                VALUES (1, 'farhandwiyan', 'tes123', 'user')
                """;

        int update = statement.executeUpdate(query);
        System.out.println(update);

        statement.close();
        con.close();
    }
}
