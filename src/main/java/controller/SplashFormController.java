package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class SplashFormController {
    public Label lblStatus;

    public void initialize(){
        Timeline timeline = new Timeline();
        var keyFrame1=new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Initializing..");
            }
        });
        var keyFrame2=new KeyFrame(Duration.millis(1000), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Loading pluging..");
            }
        });
        var keyFrame3=new KeyFrame(Duration.millis(1500), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                lblStatus.setText("Loading UI..");
            }
        });
        var keyFrame4=new KeyFrame(Duration.millis(1600), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Parent container= null;
                try {
                    container = FXMLLoader.load(this.getClass().getResource("/view/TextEditorForm.fxml"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                Scene scene = new Scene(container);
                Stage primaryStage = new Stage();
                primaryStage.setScene(scene);
                primaryStage.setTitle("Editor - DEP9");
                primaryStage.show();
                lblStatus.getScene().getWindow().hide();
            }
        });

        timeline.getKeyFrames().addAll(keyFrame1,keyFrame2,keyFrame3,keyFrame4);
        timeline.playFromStart();
    }
}
