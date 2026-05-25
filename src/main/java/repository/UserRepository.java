package repository;

import java.sql.*;
import java.util.*;
import javax.sql.DataSource;

import model.User;

public class UserRepository extends BaseRepository implements Repository<User> {
    public UserRepository(DataSource dataSource) {
        super(dataSource);
    }

    // simpan user ke database
    @Override
    public void save(User user) throws SQLException {
        String query =
            "INSERT INTO users(username, password, peran) VALUES('" +
            user.getUsername() + "', '" +
            user.getPassword() + "', '" +
            user.getPeran() + "')";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public void deleteUser(String username, String password) throws SQLException {
        String query = "DELETE FROM users WHERE username='" + username + "' AND password='"+ password +"'";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public User find(String username, String password) throws SQLException {
        String query = "SELECT * FROM users WHERE username='" + username + "' AND password='"+ password +"'";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("peran")
                );
            }
        }

        return null; 
    }

    @Override
    public List<User> findAll() throws Exception{
        List<User> listUser = new ArrayList<>();
        String query = "SELECT * FROM users";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("peran")
                );

                listUser.add(user);
            }
        }

        return listUser;
    }
}
