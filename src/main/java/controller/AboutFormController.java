package controller;

import javafx.animation.FadeTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class AboutFormController {
    public ImageView imgIcon;
    public AnchorPane anchrAbout;

    public void initialize(){
        /*ScaleTransition st = new ScaleTransition(Duration.millis(750),imgIcon);
        st.setByX(0);
        st.setByY(1);
        st.playFromStart();*/

        /*RotateTransition rt = new RotateTransition(Duration.millis(1000), imgIcon);
        rt.setFromAngle(0);
        rt.setFromAngle(360);
        rt.playFromStart();
*/
        FadeTransition ft = new FadeTransition(Duration.millis(2000), anchrAbout);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.playFromStart();
    }
}
