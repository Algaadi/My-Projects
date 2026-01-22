package UI;

import Manager.GameFlowManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * القائمة الرئيسية (MainMenu) - الآن متوافقة مع GameFlowManager
 */
public class MainMenu {
    private final Scene scene;

    public MainMenu(GameFlowManager flow) {
        Button btnStart = new Button("ابدأ اللعبة");
        Button btnLevels = new Button("المستويات");
        Button btnExit = new Button("خروج");

        btnStart.setOnAction(e -> flow.showModeSelection());
        btnLevels.setOnAction(e -> flow.showLevels());
        btnExit.setOnAction(e -> flow.getStage().close());

        VBox root = new VBox(12, btnStart, btnLevels, btnExit);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");

        this.scene = new Scene(root, 600, 400);
    }

    public Scene getScene() { return scene; }
}
