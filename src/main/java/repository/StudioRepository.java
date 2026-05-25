package repository;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import java.sql.*;

import model.Studio;

public class StudioRepository extends BaseRepository implements Repository<Studio> {
    
    public StudioRepository(DataSource dataSource) {
        super(dataSource);
    }
    
    @Override 
    public void save(Studio studio) throws Exception {
        String query =
            "INSERT INTO studios(no_studio, kapasitas) VALUES('" +
            studio.getNoStudio() + "', " +
            studio.getKapasitas() + ")";
    
        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public void deleteStudio(String noStudio) throws SQLException {
        String query = "DELETE FROM studios WHERE no_studio='" + noStudio + "'";

        try (Connection con = dataSource.getConnection();) {
            Statement statement = con.createStatement();
            statement.executeUpdate(query);
        }
    }

    public Studio findByNo(String noStudio) throws SQLException {
        String query = "SELECT * FROM studios WHERE no_studio='" + noStudio + "'";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                 return new Studio(
                    rs.getInt("id"),
                    Integer.parseInt(rs.getString("no_studio")),
                    rs.getInt("kapasitas")
                );
            }
        }

        return null; 
    }

    public Studio findById(int id) throws SQLException {
        String query = "SELECT * FROM studios WHERE id = " + id;

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            if (rs.next()) {
                 return new Studio(
                    rs.getInt("id"),
                    Integer.parseInt(rs.getString("no_studio")),
                    rs.getInt("kapasitas")
                );
            }
        }

        return null; 
    }

    @Override 
    public List<Studio> findAll() throws Exception {
        List<Studio> listStudio = new ArrayList<>();
        String query = "SELECT * FROM studios";

        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                Studio studio = new Studio(
                rs.getInt("id"),
                Integer.parseInt(rs.getString("no_studio")),
                rs.getInt("kapasitas")
            );

                listStudio.add(studio);
            }
        }

        return listStudio;
    }
}
