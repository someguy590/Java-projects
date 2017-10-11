package IPDisplay;

import Main.Main_GUI;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * Created by someguy590 on 6/10/2016.
 */
public class LogDataIPTypePane extends VBox {
    ComboBox<String> comBoxIpType;

    LogDataIPTypePane() {
        // right part of center pane which tells what information line of string has
        ComboBox<String> comBoxIpType = new ComboBox<>();
        comBoxIpType.getItems().addAll(Main_GUI.IP_TYPES);
        VBox rightPane = new VBox(comBoxIpType);
    }
}
