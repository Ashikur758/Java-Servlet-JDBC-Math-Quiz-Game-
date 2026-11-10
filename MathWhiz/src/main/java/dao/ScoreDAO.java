package dao;

import model.GameScore;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {

    // ---- change these to match your MySQL ----
    private static final String URL  = "jdbc:mysql://localhost:3306/math_whiz_db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "Ashik@123";

    private Connection getConnection() throws SQLException {
        try { Class.forName("com.mysql.cj.jdbc.Driver"); }
        catch (ClassNotFoundException e) { throw new SQLException("MySQL driver not found", e); }
        return DriverManager.getConnection(URL, USER, PASS);
    }

    public void save(GameScore g) throws SQLException {
        String sql = "INSERT INTO game_scores (player_name, score, difficulty, played_at) VALUES (?, ?, ?, ?)";
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, g.getPlayerName());
            ps.setInt(2, g.getScore());
            ps.setString(3, g.getDifficulty());
            ps.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            ps.executeUpdate();
        }
    }

    public List<GameScore> getTop10() throws SQLException {
        String sql = "SELECT * FROM game_scores ORDER BY score DESC, played_at ASC LIMIT 10";
        List<GameScore> list = new ArrayList<>();
        try (Connection c = getConnection(); PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                GameScore g = new GameScore();
                g.setId(rs.getInt("id"));
                g.setPlayerName(rs.getString("player_name"));
                g.setScore(rs.getInt("score"));
                g.setDifficulty(rs.getString("difficulty"));
                g.setPlayedAt(rs.getTimestamp("played_at"));
                list.add(g);
            }
        }
        return list;
    }
}
