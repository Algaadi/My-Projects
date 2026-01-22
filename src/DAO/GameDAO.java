package DAO;

import Classes.Game;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class GameDAO {

    public void insert(Game game) {
        String sql = "INSERT INTO Games (GameID, DatePlayed, Duration, MaxPlayers, GameMode) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, game.getGame_id());
            stmt.setDate(2, new java.sql.Date(game.getDate_played().getTime()));
            stmt.setInt(3, game.getDuration());
            stmt.setInt(4, game.getMax_players());
            stmt.setString(5, game.getGame_mode());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Game getById(int id) {
        String sql = "SELECT * FROM Games WHERE GameID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Game(
                        rs.getInt("GameID"),
                        null, null, null,
                        rs.getDate("DatePlayed"),
                        rs.getInt("Duration"),
                        rs.getInt("MaxPlayers"),
                        rs.getString("GameMode")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public DoubleCircularLinkedList<Game> getAll() {
        DoubleCircularLinkedList<Game> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Games";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Game(
                        rs.getInt("GameID"),
                        null, null, null,
                        rs.getDate("DatePlayed"),
                        rs.getInt("Duration"),
                        rs.getInt("MaxPlayers"),
                        rs.getString("GameMode")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void update(Game game) {
        String sql = "UPDATE Games SET DatePlayed = ?, Duration = ?, MaxPlayers = ?, GameMode = ? WHERE GameID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(game.getDate_played().getTime()));
            stmt.setInt(2, game.getDuration());
            stmt.setInt(3, game.getMax_players());
            stmt.setString(4, game.getGame_mode());
            stmt.setInt(5, game.getGame_id());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Games WHERE GameID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
