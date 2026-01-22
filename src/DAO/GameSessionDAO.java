package DAO;

import Classes.GameSession;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class GameSessionDAO {

    public void insert(GameSession session) {
        String sql = "INSERT INTO Game_Sessions (SessionID, DateTime, GameMode, GameID, PlayerID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, session.getSession_id());
            stmt.setTimestamp(2, new Timestamp(session.getStart_time().getTime()));
            stmt.setString(3, session.getGame().getGame_mode());
            stmt.setInt(4, session.getGame().getGame_id());
            stmt.setInt(5, session.getPlayers().get(0).getPlayer_id()); // أول لاعب فقط
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public GameSession getById(int id) {
        String sql = "SELECT * FROM Game_Sessions WHERE SessionID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new GameSession(
                        rs.getInt("SessionID"),
                        null, null, null,
                        rs.getTimestamp("DateTime"),
                        null
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public DoubleCircularLinkedList<GameSession> getAll() {
        DoubleCircularLinkedList<GameSession> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Game_Sessions";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new GameSession(
                        rs.getInt("SessionID"),
                        null, null, null,
                        rs.getTimestamp("DateTime"),
                        null
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Game_Sessions WHERE SessionID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
