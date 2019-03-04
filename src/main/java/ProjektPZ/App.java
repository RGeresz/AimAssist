package ProjektPZ;

import ProjektPZ.controllers.MenuController;
import ProjektPZ.utils.PlatformUtil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class App extends Application {

    public static AppState state = AppState.STOPPED;

    public static MenuController menuController;


    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("/fxml/test.fxml"));
        Pane pane = fxmlLoader.load();
        Scene scene = new Scene(pane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AimAssist");
        primaryStage.setResizable(false);
        primaryStage.show();
        menuController = fxmlLoader.getController();
        primaryStage.setOnCloseRequest(event -> menuController.close());
        menuController.setStage(primaryStage);
    }

    public static void setState(AppState state) {
        App.state = state;
        PlatformUtil.run(() -> App.menuController.setAppStateLabel(getState()));
    }

    public static AppState getState() {
        return state;
    }
}
