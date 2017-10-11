package IPDisplay;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

/**
 * Created by someguy590 on 6/10/2016.
 */
public class CheckBoxesPane extends VBox {
    private ArrayList<CheckBox> checkBoxes;

    CheckBoxesPane() {
        // left part of center pane which tells if line of text is selected
        checkBoxes = new ArrayList<>();
        checkBoxes.add(new CheckBox());
        this.getChildren().addAll(checkBoxes);
    }
}
