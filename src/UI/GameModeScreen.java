package UI;

import Manager.GameFlowManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/*
 شاشة اختيار طور اللعبة - متصلة مع GameFlowManager
*/
public class GameModeScreen {
    private final GameFlowManager manager;
    private final Scene scene;

    public GameModeScreen(GameFlowManager manager) {
        this.manager = manager;

        Button single = new Button("Single Player");
        Button multi = new Button("Multiplayer (TBD)");
        Button back = new Button("رجوع");

        single.setOnAction(e -> manager.showLevels());
        multi.setOnAction(e -> manager.showLevels()); // مؤقتًا نفس الشي
        back.setOnAction(e -> manager.showMainMenu());

        HBox h = new HBox(10, single, multi);
        h.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, h, back);
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-padding:20");

        this.scene = new Scene(root, 480, 360);
    }

    public Scene getScene() {
        return scene;
    }
}
