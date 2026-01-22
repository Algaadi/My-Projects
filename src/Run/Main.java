package Run;

import Manager.GameFlowManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        GameFlowManager flow = new GameFlowManager(primaryStage);
        flow.showMainMenu();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
