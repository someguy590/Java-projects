package Main; /**
 * Created by someguy590 on 6/5/2016.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main_GUI extends Application {
    public final static String[] IP_TYPES = {"NPC", "VPC", "Account", "Key", "None"};
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
        HackerExperienceHelper helper = new HackerExperienceHelper();

        // top pane that lets user select or sort lines of text
        Label lblSelect = new Label("Select:");
        ComboBox<String> comBoxSelect = new ComboBox();
        comBoxSelect.getItems().add("All");
        comBoxSelect.getItems().addAll(IP_TYPES);
        comBoxSelect.setValue(comBoxSelect.getItems().get(0));

        Label lblSort = new Label("Sort:");
        ComboBox<String> comBoxSort = new ComboBox();
        comBoxSort.getItems().addAll(IP_TYPES);
        comBoxSort.setValue(comBoxSort.getItems().get(0));

        HBox topPane = new HBox(lblSelect, comBoxSelect, lblSort, comBoxSort);

        // left part of center pane which tells if line of text is selected
        ArrayList<CheckBox> checkBoxes = new ArrayList<>();
        checkBoxes.add(new CheckBox());
        VBox leftPane = new VBox();
        leftPane.getChildren().addAll(checkBoxes);

        // center part of center pane which holds text
        TextArea textArea = new TextArea();
        textArea.textProperty().addListener(e -> {
            int paragraphCount = textArea.getParagraphs().size();
            if (paragraphCount == checkBoxes.size())
                return;


        });

        // right part of center pane which tells what information line of string has
        ComboBox<String> comBoxIpType = new ComboBox<>();
        comBoxIpType.getItems().addAll(IP_TYPES);
        VBox rightPane = new VBox(comBoxIpType);

        // center pane
        HBox centerPane = new HBox(leftPane, textArea, rightPane);

        // bottom pane which lets user manipulate selected lines of text
        Label lblRemove = new Label("Remove:");
        ComboBox<String> comBoxRemove = new ComboBox();
        comBoxRemove.getItems().add("All");
        comBoxRemove.getItems().addAll(IP_TYPES);
        comBoxRemove.setValue(comBoxRemove.getItems().get(0));

        HBox bottomPane = new HBox(lblRemove, comBoxRemove);

        // root pane
        BorderPane root = new BorderPane();
        root.setTop(topPane);
        root.setCenter(centerPane);
        root.setBottom(bottomPane);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
