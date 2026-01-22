package UI;

import Manager.GameFlowManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

/**
 * شاشة إعداد اللعبة: عدد لاعبين، نوع التحدي، نمط الفوز (Time/Points)، مدة، عدد العناصر، نقاط الهدف
 */
public class SetupGameScreen {
    private final Scene scene;

    public SetupGameScreen(GameFlowManager flow) {
        Label lblPlayers = new Label("اختر عدد اللاعبين:");
        Spinner<Integer> spnPlayers = new Spinner<>(1, 4, 1);

        Label lblChallenge = new Label("اختر نوع التحدي:");
        ComboBox<String> cmbChallenge = new ComboBox<>();
        cmbChallenge.getItems().addAll("Letters", "Words", "Symbols", "All");
        cmbChallenge.setValue("Letters");

        Label lblMode = new Label("اختر نمط الفوز:");
        ComboBox<String> cmbMode = new ComboBox<>();
        cmbMode.getItems().addAll("Time", "Points", "Items");
        cmbMode.setValue("Time");

        Label lblTime = new Label("المدة بالثواني:");
        Spinner<Integer> spnTime = new Spinner<>(10, 600, 60);

        Label lblItems = new Label("عدد العناصر (لكل لاعب):");
        Spinner<Integer> spnItems = new Spinner<>(10, 500, 50);

        Label lblPoints = new Label("نقطة الهدف:");
        Spinner<Integer> spnPoints = new Spinner<>(10, 2000, 100);

        Button btnStart = new Button("ابدأ اللعب");
        Button btnBack = new Button("رجوع");

        // إذا اختار Points -> يفرض challenge = All (كما طلبت)
        cmbMode.setOnAction(e -> {
            if ("Points".equals(cmbMode.getValue())) {
                cmbChallenge.setValue("All");
                cmbChallenge.setDisable(true);
            } else {
                cmbChallenge.setDisable(false);
            }
        });

        btnStart.setOnAction(e -> {
            int players = spnPlayers.getValue();
            String challenge = cmbChallenge.getValue();
            String mode = cmbMode.getValue();
            int time = spnTime.getValue();
            int items = spnItems.getValue();
            int points = spnPoints.getValue();

            flow.showGameplay(players, challenge, mode, time, items, points);
        });

        btnBack.setOnAction(e -> flow.showModeSelection());

        VBox root = new VBox(10,
                lblPlayers, spnPlayers,
                lblChallenge, cmbChallenge,
                lblMode, cmbMode,
                lblTime, spnTime,
                lblItems, spnItems,
                lblPoints, spnPoints,
                btnStart, btnBack
        );
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");

        this.scene = new Scene(root, 520, 560);
    }

    public Scene getScene() { return scene; }
}
