package DAO;

import Classes.Level;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class LevelDAO {

    public void insert(Level level) {
        String sql = "INSERT INTO Levels (LevelID, Type, Difficulty, TimeLimit, SymbolCount, GameID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, level.getLevel_id());
            stmt.setString(2, level.getLevel_name());
            stmt.setString(3, level.getDifficulty());
            stmt.setInt(4, level.getTime_limit());
            stmt.setInt(5, level.getSymbol_count());
            stmt.setInt(6, 1); // مؤقتًا مربوط بلعبة ID=1
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Level getById(int id) {
        String sql = "SELECT * FROM Levels WHERE LevelID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Level(
                        rs.getInt("LevelID"),
                        rs.getString("Type"),
                        rs.getInt("TimeLimit"),
                        rs.getInt("SymbolCount"),
                        rs.getString("Difficulty")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public DoubleCircularLinkedList<Level> getAll() {
        DoubleCircularLinkedList<Level> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Levels";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Level(
                        rs.getInt("LevelID"),
                        rs.getString("Type"),
                        rs.getInt("TimeLimit"),
                        rs.getInt("SymbolCount"),
                        rs.getString("Difficulty")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void update(Level level) {
        String sql = "UPDATE Levels SET Type=?, Difficulty=?, TimeLimit=?, SymbolCount=? WHERE LevelID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, level.getLevel_name());
            stmt.setString(2, level.getDifficulty());
            stmt.setInt(3, level.getTime_limit());
            stmt.setInt(4, level.getSymbol_count());
            stmt.setInt(5, level.getLevel_id());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Levels WHERE LevelID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
