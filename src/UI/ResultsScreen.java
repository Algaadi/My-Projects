package UI;

import Manager.GameFlowManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * شاشة النتائج - تدعم عرض نتائج من مصفوفة أو عرض مفرد (score, avg)
 */
public class ResultsScreen {
    private final Scene scene;

    // Constructor: عرض بسيط score + avg
    public ResultsScreen(GameFlowManager flow, int score, double avgReaction) {
        Label lblScore = new Label("Score: " + score);
        Label lblAvg = new Label(String.format("Avg Reaction: %.1f ms", avgReaction));
        Button btnMain = new Button("القائمة الرئيسية");
        Button btnReplay = new Button("ابدأ مرة أخرى");

        btnMain.setOnAction(e -> flow.showMainMenu());
        btnReplay.setOnAction(e -> flow.showModeSelection());

        VBox root = new VBox(12, lblScore, lblAvg, btnReplay, btnMain);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");
        this.scene = new Scene(root, 480, 360);
    }

    // Constructor: عرض نتائج من مصفوفة النقاط (يعرض الفائز)
    public ResultsScreen(GameFlowManager flow, int[] scores) {
        int players = scores != null ? scores.length : 0;
        StringBuilder sb = new StringBuilder();
        int best = -1, bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < players; i++) {
            sb.append("Player ").append(i+1).append(": ").append(scores[i]).append("\n");
            if (scores[i] > bestScore) { bestScore = scores[i]; best = i; }
        }
        Label lblAll = new Label(sb.toString());
        Label lblWinner = new Label(best >= 0 ? ("Winner: Player " + (best+1) + " (" + bestScore + ")") : "No winner");
        Button btnMain = new Button("القائمة الرئيسية");
        Button btnReplay = new Button("إعادة اللعب");

        btnMain.setOnAction(e -> flow.showMainMenu());
        btnReplay.setOnAction(e -> flow.showModeSelection());

        VBox root = new VBox(12, lblAll, lblWinner, btnReplay, btnMain);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");
        this.scene = new Scene(root, 480, 360);
    }

    // Small helper in case old calls used ResultsScreen(scores) without flow:
    public ResultsScreen(int[] scores) {
        // create a minimal scene (no navigation)
        int players = scores != null ? scores.length : 0;
        StringBuilder sb = new StringBuilder();
        int best = -1, bestScore = Integer.MIN_VALUE;
        for (int i = 0; i < players; i++) {
            sb.append("Player ").append(i+1).append(": ").append(scores[i]).append("\n");
            if (scores[i] > bestScore) { bestScore = scores[i]; best = i; }
        }
        Label lblAll = new Label(sb.toString());
        Label lblWinner = new Label(best >= 0 ? ("Winner: Player " + (best+1) + " (" + bestScore + ")") : "No winner");
        VBox root = new VBox(12, lblAll, lblWinner);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");
        this.scene = new Scene(root, 480, 360);
    }

    public Scene getScene() { return scene; }
}
