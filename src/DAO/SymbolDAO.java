package DAO;

import Classes.Symbol;
import Manager.DatabaseManager;

import java.sql.*;
import DataStructures.DoubleCircularLinkedList;
public class SymbolDAO {

    public void insert(Symbol symbol) {
        String sql = "INSERT INTO Symbols (SymbolID, SymbolType, SymbolValue, Points, GameID) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, symbol.getSymbol_id());
            stmt.setString(2, symbol.getSymbol_type());
            stmt.setString(3, symbol.getSymbol_value());
            stmt.setInt(4, symbol.getPoints());
            stmt.setInt(5, 1); // مؤقتًا مربوط بلعبة ID=1
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    public Symbol getById(int id) {
        String sql = "SELECT * FROM Symbols WHERE SymbolID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Symbol(
                        rs.getInt("SymbolID"),
                        rs.getString("SymbolType"),
                        rs.getString("SymbolValue"),
                        rs.getInt("Points")
                );
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return null;
    }

    public DoubleCircularLinkedList<Symbol> getAll() {
        DoubleCircularLinkedList<Symbol> list = new DoubleCircularLinkedList<>();
        String sql = "SELECT * FROM Symbols";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Symbol(
                        rs.getInt("SymbolID"),
                        rs.getString("SymbolType"),
                        rs.getString("SymbolValue"),
                        rs.getInt("Points")
                ));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public void delete(int id) {
        String sql = "DELETE FROM Symbols WHERE SymbolID=?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
}
