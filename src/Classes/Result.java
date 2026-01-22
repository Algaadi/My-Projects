package Classes;

public class Result {
    private int result_id;
    private long reaction_time;
    private int score;
    private int rank;
    private String status;

    public Result() {
    }

    public Result(int result_id, long reaction_time, int score, int rank, String status) {
        this.result_id = result_id;
        this.reaction_time = reaction_time;
        this.score = score;
        this.rank = rank;
        this.status = status;
    }

    public int getResult_id() {
        return result_id;
    }

    public void setResult_id(int result_id) {
        this.result_id = result_id;
    }

    public long getReaction_time() {
        return reaction_time;
    }

    public void setReaction_time(long reaction_time) {
        this.reaction_time = reaction_time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Result{" +
                "result_id=" + result_id +
                ", reaction_time=" + reaction_time +
                ", score=" + score +
                ", rank=" + rank +
                ", status='" + status + '\'' +
                '}';
    }
}
