package model;

import java.sql.Timestamp;

public class GameScore {
    private int id;
    private String playerName;
    private int score;
    private String difficulty;
    private Timestamp playedAt;

    public GameScore() {}
    public GameScore(String playerName, int score, String difficulty) {
        this.playerName = playerName; this.score = score; this.difficulty = difficulty;
    }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getPlayerName() { return playerName; }
    public void setPlayerName(String p) { this.playerName = p; }
    public int getScore() { return score; }
    public void setScore(int s) { this.score = s; }
    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String d) { this.difficulty = d; }
    public Timestamp getPlayedAt() { return playedAt; }
    public void setPlayedAt(Timestamp t) { this.playedAt = t; }
}
