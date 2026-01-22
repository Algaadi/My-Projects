package DAO;

import Classes.Challenge;
import Classes.LetterChallenge;
import Classes.ShapeChallenge;
import Classes.WordChallenge;
import DataStructures.DoubleCircularLinkedList;
import Manager.DatabaseManager;

import java.sql.*;

public class ChallengeDAO {

    public void insert(Challenge challenge) {
        String sql = "INSERT INTO Challenges (ChallengeID, Type, Description) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, challenge.getId());
            stmt.setString(2, normalizeType(challenge.getType()));
            stmt.setString(3, challenge.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Challenge getById(int id) {
        String sql = "SELECT * FROM Challenges WHERE ChallengeID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int challengeId = rs.getInt("ChallengeID");
                String type = rs.getString("Type");
                String desc = rs.getString("Description");
                return createChallengeByType(challengeId, desc, type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public DoubleCircularLinkedList<Challenge> getAll() {
        DoubleCircularLinkedList<Challenge> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Challenges";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int challengeId = rs.getInt("ChallengeID");
                String type = rs.getString("Type");
                String desc = rs.getString("Description");
                list.add(createChallengeByType(challengeId, desc, type));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void update(Challenge challenge) {
        String sql = "UPDATE Challenges SET Type = ?, Description = ? WHERE ChallengeID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, normalizeType(challenge.getType()));
            stmt.setString(2, challenge.getDescription());
            stmt.setInt(3, challenge.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Challenges WHERE ChallengeID = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * تخزين نوع التحدي بشكل موحّد في قاعدة البيانات.
     */
    private String normalizeType(String type) {
        if (type == null) return "word";
        String t = type.trim().toLowerCase();
        if (t.contains("letter")) return "letter";
        if (t.contains("word")) return "word";
        if (t.contains("symbol") || t.contains("shape")) return "symbol";
        return t;
    }

    /**
     * إنشاء كائن Challenge بناء على type المخزّن في قاعدة البيانات.
     */
    private Challenge createChallengeByType(int id, String desc, String type) {
        String t = normalizeType(type);

        switch (t) {
            case "letter":
                return new LetterChallenge(id, "Letter", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
            case "symbol":
                return new ShapeChallenge(id, "Symbol", "★,☆,@,#,$,%,&");
            case "word":
            default:
                // fallback: نرجع WordChallenge كنوع افتراضي
                return new WordChallenge(id, "Word", (desc == null || desc.isBlank())
                        ? "Java,Code,Play,Fast,Challenge,Win"
                        : desc);
        }
    }
}
