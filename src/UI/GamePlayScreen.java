package UI;

import Manager.GameFlowManager;
import Manager.GameManager;
import Classes.Challenge;
import Classes.LetterChallenge;
import Classes.ShapeChallenge;
import Classes.WordChallenge;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.util.Duration;

import java.util.Random;

/**
 * شاشة اللعب:
 * - تقسم الشاشة حسب عدد اللاعبين
 * - تولّد رموز/حروف/كلمات عشوائية داخل كل مساحة لاعب
 * - يدعم أوضاع الفوز: Time, Points, Items
 */
public class GamePlayScreen {
    private final Scene scene;
    private final GameFlowManager flow;
    private final GameManager gm;

    private final int players;
    private final String challengeType;
    private final String winMode;
    private final int timeLimit;   // بالثواني
    private final int itemsLimit;  // عدد العناصر لكل لاعب (Items mode) أو عدد العناصر المراد عرضها/استهدافها
    private final int pointsTarget; // في حالة Points mode

    private final int[] scores;
    private final int[] itemsHit;

    private final Label[] scoreLabels;
    private final Pane[] playPanes;
    private final Label[] timeLabels;

    private final Random rnd = new Random();
    private int remainingTime;
    private Timeline gameTimer;

    public GamePlayScreen(GameFlowManager flow, int players, String challengeType, String winMode, int timeLimit, int itemsLimit, int pointsTarget) {
        this.flow = flow;
        this.gm = flow.getGameManager();

        this.players = Math.max(1, players);
        this.challengeType = challengeType;
        this.winMode = winMode;
        this.timeLimit = Math.max(1, timeLimit);
        this.itemsLimit = Math.max(1, itemsLimit);
        this.pointsTarget = Math.max(1, pointsTarget);

        this.scores = new int[this.players];
        this.itemsHit = new int[this.players];
        this.scoreLabels = new Label[this.players];
        this.timeLabels = new Label[this.players];
        this.playPanes = new Pane[this.players];

        this.remainingTime = this.timeLimit;

        BorderPane root = new BorderPane();
        HBox playersRow = new HBox(6);
        playersRow.setAlignment(Pos.CENTER);

        double paneWidth = Math.max(150, 900.0 / this.players);
        double paneHeight = 480;

        for (int i = 0; i < this.players; i++) {
            VBox playerArea = new VBox(8);
            playerArea.setAlignment(Pos.TOP_CENTER);
            playerArea.setStyle("-fx-border-color:#999; -fx-padding:10; -fx-background-color: white;");
            playerArea.setPrefWidth(paneWidth);

            Label title = new Label("Player " + (i + 1));
            Label sc = new Label("Score: 0");
            Label tm = new Label("Time: " + this.remainingTime);
            Pane playPane = new Pane();
            playPane.setPrefSize(paneWidth - 10, paneHeight);
            playPane.setStyle("-fx-background-color:transparent;");

            scoreLabels[i] = sc;
            timeLabels[i] = tm;
            playPanes[i] = playPane;

            playerArea.getChildren().addAll(title, sc, tm, playPane);
            playersRow.getChildren().add(playerArea);

            // generate first challenge for this player
            spawnChallengeForPlayer(i);
        }

        root.setCenter(playersRow);
        this.scene = new Scene(root, Math.min(1400, paneWidth * this.players + 40), paneHeight + 150);

        // Start one global timer for the match (counts down)
        gameTimer = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            remainingTime--;
            // update player timers labels
            for (int i = 0; i < players; i++) {
                timeLabels[i].setText("Time: " + remainingTime);
            }

            if (remainingTime <= 0) {
                endGameByTime();
            }
        }));
        gameTimer.setCycleCount(Timeline.INDEFINITE);
        gameTimer.play();
    }

    public Scene getScene() { return scene; }

    // جنيريك لتوليد challenge object حسب النوع (لكن نستخدم generateNode-like behavior عبر JavaFX Node)
    private Challenge createChallengeObj(String forcedType) {
        String t = forcedType != null ? forcedType : this.challengeType;
        if ("Letters".equalsIgnoreCase(t)) return new LetterChallenge(1, "Letter", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if ("Words".equalsIgnoreCase(t)) return new WordChallenge(2, "Word", "Java,Code,Play,Fast,Challenge,Win");
        if ("Symbols".equalsIgnoreCase(t)) return new ShapeChallenge(3, "Symbol", "★,☆,@,#,$,%,&");
        // All -> random pick
        int k = rnd.nextInt(3);
        if (k == 0) return new LetterChallenge(1, "Letter", "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        if (k == 1) return new WordChallenge(2, "Word", "Java,Code,Play,Fast,Challenge,Win");
        return new ShapeChallenge(3, "Symbol", "★,☆,@,#,$,%,&");
    }

    // spawn challenge for player i inside playPanes[i]
    private void spawnChallengeForPlayer(int playerIndex) {
        Pane pane = playPanes[playerIndex];
        // generate visible text (we'll use simple Label clickable)
        String text = generateRandomTextForChallenge(this.challengeType);

        Label node = new Label(text);
        node.setStyle("-fx-font-size: 36px; -fx-font-weight: bold; -fx-text-fill: black;");

        double w = pane.getPrefWidth();
        double h = pane.getPrefHeight();

        // random position inside pane bounds (leave margin)
        double x = Math.max(0, rnd.nextDouble() * Math.max(1, w - 50));
        double y = Math.max(0, rnd.nextDouble() * Math.max(1, h - 30));
        node.setLayoutX(x);
        node.setLayoutY(y);

        // click handler
        node.setOnMouseClicked(e -> {
            int pts = pointsForText(text);
            scores[playerIndex] += pts;
            itemsHit[playerIndex]++;
            scoreLabels[playerIndex].setText("Score: " + scores[playerIndex]);

            // remove clicked node
            pane.getChildren().remove(node);

            // check win condition after hit
            if (checkWinCondition(playerIndex)) {
                endGameByWinner(playerIndex);
                return;
            }

            // spawn next challenge for this player (immediately)
            spawnChallengeForPlayer(playerIndex);
        });

        // add to pane
        pane.getChildren().add(node);
    }

    private String generateRandomTextForChallenge(String challenge) {
        if ("Letters".equalsIgnoreCase(challenge)) {
            return String.valueOf((char) ('A' + rnd.nextInt(26)));
        } else if ("Words".equalsIgnoreCase(challenge)) {
            String[] words = {"Java", "Code", "Play", "Fast", "Challenge", "Win", "Quick"};
            return words[rnd.nextInt(words.length)];
        } else if ("Symbols".equalsIgnoreCase(challenge)) {
            String[] symbols = {"★", "☆", "@", "#", "$", "%", "&"};
            return symbols[rnd.nextInt(symbols.length)];
        } else { // All
            int k = rnd.nextInt(3);
            if (k == 0) return String.valueOf((char) ('A' + rnd.nextInt(26)));
            if (k == 1) { String[] words = {"Java","Code","Play","Fast"}; return words[rnd.nextInt(words.length)]; }
            String[] symbols = {"★","☆","@","#","$"}; return symbols[rnd.nextInt(symbols.length)];
        }
    }

    private int pointsForText(String txt) {
        // apply points logic: letters 8, words 10, symbols 15
        if (txt.length() == 1 && Character.isLetter(txt.charAt(0))) return 8;
        if (txt.length() == 1 && !Character.isLetter(txt.charAt(0))) return 15;
        return 10;
    }

    private boolean checkWinCondition(int playerIndex) {
        if ("Points".equalsIgnoreCase(winMode)) {
            return scores[playerIndex] >= pointsTarget;
        } else if ("Items".equalsIgnoreCase(winMode)) {
            return itemsHit[playerIndex] >= itemsLimit;
        } else { // Time mode: game ends by timer
            return false;
        }
    }

    private void endGameByWinner(int winnerIndex) {
        if (gameTimer != null) gameTimer.stop();
        // prepare scores array
        int[] result = new int[players];
        System.arraycopy(scores, 0, result, 0, players);
        flow.showResults(result);
    }

    private void endGameByTime() {
        if (gameTimer != null) gameTimer.stop();
        int[] result = new int[players];
        System.arraycopy(scores, 0, result, 0, players);
        flow.showResults(result);
    }
}
