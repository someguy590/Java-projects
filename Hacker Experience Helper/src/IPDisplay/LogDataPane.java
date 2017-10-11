package IPDisplay;
/**
 * Created by someguy590 on 6/9/2016.
 */

import javafx.application.Application;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LogDataPane extends Pane {
    private TextArea textArea;
    LogDataPane() {
        // center part of center pane which holds text
        TextArea textArea = new TextArea();
    }
}
