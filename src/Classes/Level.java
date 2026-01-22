package Classes;

public class Level {
    private int levelId;
    private String levelName;
    private int timeLimit;     // seconds
    private int symbolCount;   // number of targets in level
    private String difficulty;

    public Level() {}

    public Level(int levelId, String levelName, int timeLimit, int symbolCount, String difficulty) {
        this.levelId = levelId;
        this.levelName = levelName;
        this.timeLimit = timeLimit;
        this.symbolCount = symbolCount;
        this.difficulty = difficulty;
    }

    public int getLevel_id() { return levelId; }
    public void setLevel_id(int levelId) { this.levelId = levelId; }

    public String getLevel_name() { return levelName; }
    public void setLevel_name(String levelName) { this.levelName = levelName; }

    public int getTime_limit() { return timeLimit; }
    public void setTime_limit(int timeLimit) { this.timeLimit = timeLimit; }

    public int getSymbol_count() { return symbolCount; }
    public void setSymbol_count(int symbolCount) { this.symbolCount = symbolCount; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "Level{" +
                "levelId=" + levelId +
                ", levelName='" + levelName + '\'' +
                ", timeLimit=" + timeLimit +
                ", symbolCount=" + symbolCount +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}
