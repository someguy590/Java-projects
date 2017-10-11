/**
 * Created by someguy590 on 5/9/2016.
 */

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        System.out.println(Boxes.sBox1.length);
        System.out.println(Boxes.sBox2.length);
        System.out.println(Boxes.sBox3.length);
        System.out.println(Boxes.sBox4.length);
    }
}
