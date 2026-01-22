package UI;

import Manager.GameFlowManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * اختيار الوضع: محلي أو بلوتوث
 */
public class ModeSelectionScreen {
    private final Scene scene;

    public ModeSelectionScreen(GameFlowManager flow) {
        Button local = new Button("اللعب على نفس الجهاز");
        Button bt = new Button("اللعب عبر البلوتوث");
        Button back = new Button("رجوع");

        local.setOnAction(e -> flow.showSetupGameScreen());
        bt.setOnAction(e -> {
            // Bluetooth mode placeholder
            System.out.println("Bluetooth mode not implemented yet.");
        });
        back.setOnAction(e -> flow.showMainMenu());

        VBox root = new VBox(12, local, bt, back);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");

        this.scene = new Scene(root, 480, 360);
    }

    public Scene getScene() { return scene; }
}
