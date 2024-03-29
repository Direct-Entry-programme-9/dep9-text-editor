import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent splashContainer=FXMLLoader.load(this.getClass().getResource("/view/SplashForm.fxml"));
        Scene splashScene = new Scene(splashContainer);
        splashScene.setFill(Color.TRANSPARENT);
        Stage splashStage = new Stage(StageStyle.TRANSPARENT);
        splashStage.setScene(splashScene);
        splashStage.show();


    }
}
