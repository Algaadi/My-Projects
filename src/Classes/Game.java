package Classes;

import java.util.Date;

public class Game {
    private int game_id;
    private Challenge challenge;
    private Level level;
    private Symbol symbol;
    private Date date_played;
    private int duration;
    private int max_players;
    private String game_mode;

    public Game() {
    }

    public Game(int game_id, Challenge challenge, Level level, Symbol symbol,
                Date date_played, int duration, int max_players, String game_mode) {
        this.game_id = game_id;
        this.challenge = challenge;
        this.level = level;
        this.symbol = symbol;
        this.date_played = date_played;
        this.duration = duration;
        this.max_players = max_players;
        this.game_mode = game_mode;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public void setChallenge(Challenge challenge) {
        this.challenge = challenge;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Date getDate_played() {
        return date_played;
    }

    public void setDate_played(Date date_played) {
        this.date_played = date_played;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getMax_players() {
        return max_players;
    }

    public void setMax_players(int max_players) {
        this.max_players = max_players;
    }

    public String getGame_mode() {
        return game_mode;
    }

    public void setGame_mode(String game_mode) {
        this.game_mode = game_mode;
    }

    public void start() {
        System.out.println("Game started!");
    }

    public void end() {
        System.out.println("Game ended!");
    }

    @Override
    public String toString() {
        return "Game{" +
                "game_id=" + game_id +
                ", challenge=" + challenge +
                ", level=" + level +
                ", symbol=" + symbol +
                ", date_played=" + date_played +
                ", duration=" + duration +
                ", max_players=" + max_players +
                ", game_mode='" + game_mode + '\'' +
                '}';
    }
}
