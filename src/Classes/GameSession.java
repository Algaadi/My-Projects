package Classes;

import DataStructures.DoubleCircularLinkedList;

import java.util.Date;

public class GameSession {
    private int session_id;
    private Game game;
    private DoubleCircularLinkedList<Player> players;
    private DoubleCircularLinkedList<Result> results;
    private Date start_time;
    private Date end_time;

    public GameSession() {
        this.players = new DoubleCircularLinkedList<>();
        this.results = new DoubleCircularLinkedList<>();
    }

    public GameSession(int session_id, Game game,
                       DoubleCircularLinkedList<Player> players,
                       DoubleCircularLinkedList<Result> results,
                       Date start_time, Date end_time) {
        this.session_id = session_id;
        this.game = game;
        this.players = (players != null) ? players : new DoubleCircularLinkedList<>();
        this.results = (results != null) ? results : new DoubleCircularLinkedList<>();
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public int getSession_id() {
        return session_id;
    }

    public void setSession_id(int session_id) {
        this.session_id = session_id;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public DoubleCircularLinkedList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(DoubleCircularLinkedList<Player> players) {
        this.players = (players != null) ? players : new DoubleCircularLinkedList<>();
    }

    public DoubleCircularLinkedList<Result> getResults() {
        return results;
    }

    public void setResults(DoubleCircularLinkedList<Result> results) {
        this.results = (results != null) ? results : new DoubleCircularLinkedList<>();
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    @Override
    public String toString() {
        return "Game_Session{" +
                "session_id=" + session_id +
                ", game=" + game +
                ", players=" + players +
                ", results=" + results +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                '}';
    }
}
