package UI;

import Classes.Level;
import DataStructures.DoubleCircularLinkedList;
import Manager.GameFlowManager;
import Manager.LevelManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

/**
 * شاشة اختيار المستويات - تربط مع LevelManager
 */
public class LevelsScreen {
    private final Scene scene;

    public LevelsScreen(GameFlowManager flow, LevelManager levelManager) {
        VBox root = new VBox(10);
        root.setStyle("-fx-padding:18; -fx-alignment:center");
        Label title = new Label("اختر المستوى");
        root.getChildren().add(title);

        DoubleCircularLinkedList<Level> levels = levelManager.getAllLevels();
        for (Level l : levels) {
            Button b = new Button(l.getLevel_name() + " (" + l.getDifficulty() + ")");
            // عند الضغط نمرر Level مباشرة (هناك overload في GameFlowManager)
            b.setOnAction(e -> flow.showGameplay(l));
            root.getChildren().add(b);
        }

        Button back = new Button("رجوع");
        back.setOnAction(e -> flow.showMainMenu());
        root.getChildren().add(back);

        this.scene = new Scene(root, 700, 500);
    }

    public Scene getScene() { return scene; }
}
