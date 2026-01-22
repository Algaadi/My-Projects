package DAO;

import Classes.Player;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class PlayerDAO {

    public void insert(Player player) {
        String sql = "INSERT INTO Players (PlayerID, Name) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, player.getPlayer_id());
            stmt.setString(2, player.getName());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Player getById(int id) {
        String sql = "SELECT * FROM Players WHERE PlayerID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Player(rs.getInt("PlayerID"), rs.getString("Name"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public DoubleCircularLinkedList<Player> getAll() {
        DoubleCircularLinkedList<Player> players = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Players";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                players.add(new Player(rs.getInt("PlayerID"), rs.getString("Name")));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return players;
    }

    public void update(Player player) {
        String sql = "UPDATE Players SET Name=? WHERE PlayerID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, player.getName());
            stmt.setInt(2, player.getPlayer_id());
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Players WHERE PlayerID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
