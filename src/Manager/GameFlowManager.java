package Manager;

import Classes.Level;
import UI.*;
import javafx.stage.Stage;

/**
 * مدير الشاشات المركزي - متوافق تماماً مع بقية الكلاسات.
 */
public class GameFlowManager {
    private final Stage stage;
    private final LevelManager levelManager;
    private final GameManager gameManager;

    public GameFlowManager(Stage stage) {
        this.stage = stage;
        this.levelManager = new LevelManager();
        this.gameManager = new GameManager();
    }

    public void showMainMenu() {
        MainMenu menu = new MainMenu(this);
        stage.setScene(menu.getScene());
        stage.setTitle("Fastest Game - Main Menu");
        stage.show();
    }

    public void showModeSelection() {
        ModeSelectionScreen ms = new ModeSelectionScreen(this);
        stage.setScene(ms.getScene());
        stage.setTitle("اختر وضع اللعب");
        stage.show();
    }

    public void showSetupGameScreen() {
        SetupGameScreen s = new SetupGameScreen(this);
        stage.setScene(s.getScene());
        stage.setTitle("إعدادات اللعبة");
        stage.show();
    }

    public void showLevels() {
        LevelsScreen ls = new LevelsScreen(this, levelManager);
        stage.setScene(ls.getScene());
        stage.setTitle("اختر المستوى");
        stage.show();
    }

    // overload: قبول Level (مكالمات سابقة كانت تقوم بذلك)
    public void showGameplay(Level level) {
        // نحول Level إلى إعدادات افتراضية أو متعلقة بالمستوى
        // مثلاً: عدد لاعبين = 1، نوع = "All"، وقت = level.getTime_limit()
        int time = level != null ? level.getTime_limit() : 60;
        showGameplay(1, "All", "Time", time, level != null ? level.getSymbol_count() : 50, 100);
    }

    // الطريقة العامة لبدء اللعب (تُستخدم من SetupGameScreen)
    public void showGameplay(int players, String challengeType, String mode, int timeSeconds, int itemsCount, int pointsTarget) {
        GamePlayScreen gps = new GamePlayScreen(this, players, challengeType, mode, timeSeconds, itemsCount, pointsTarget);
        stage.setScene(gps.getScene());
        stage.setTitle("Fastest Game - Play");
        stage.show();
    }

    // عرض النتائج من مصفوفة النقاط
    public void showResults(int[] scores) {
        ResultsScreen rs = new ResultsScreen(this, scores);
        stage.setScene(rs.getScene());
        stage.setTitle("نتائج اللعبة");
        stage.show();
    }

    // عرض نتائج مبسطة (score, avg) — احتفظنا له للتوافق
    public void showResults(int score, double avg) {
        ResultsScreen rs = new ResultsScreen(this, score, avg);
        stage.setScene(rs.getScene());
        stage.setTitle("نتائج اللعبة");
        stage.show();
    }

    // getters
    public Stage getStage() { return stage; }
    public LevelManager getLevelManager() { return levelManager; }
    public GameManager getGameManager() { return gameManager; }
}
