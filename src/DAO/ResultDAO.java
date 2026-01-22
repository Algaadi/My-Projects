package DAO;

import Classes.Result;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class ResultDAO {

    public void insert(Result result) {
        String sql = "INSERT INTO Results (ResultID, ReactionTime, Accuracy, Score, Status, SessionID) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, result.getResult_id());
            stmt.setTimestamp(2, new Timestamp(result.getReaction_time()));
            stmt.setInt(3, result.getRank());   // ملاحظة: هنا استخدمنا rank كـ Accuracy
            stmt.setInt(4, result.getScore());
            stmt.setString(5, result.getStatus());
            stmt.setInt(6, 1); // مؤقتًا مربوط بجلسة ID=1
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public DoubleCircularLinkedList<Result> getAll() {
        DoubleCircularLinkedList<Result> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Results";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Result(
                        rs.getInt("ResultID"),
                        rs.getTimestamp("ReactionTime").getTime(),
                        rs.getInt("Score"),
                        rs.getInt("Accuracy"),
                        rs.getString("Status")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Results WHERE ResultID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
