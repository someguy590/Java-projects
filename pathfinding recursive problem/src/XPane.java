import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Line;

/**
 * Created by someguy590 on 12/21/2016.
 */
public class XPane extends Pane {
    private boolean isMarked = false;

    XPane() {
        this.setStyle("-fx-border-color: black");

        this.setOnMouseClicked(event -> {
            if (isMarked) {
                this.getChildren().clear();
                isMarked = false;
                return;
            }

            Line line1 = new Line(0, 0, getWidth(), getHeight());
            line1.endXProperty().bind(widthProperty());
            line1.endYProperty().bind(heightProperty());

            Line line2 = new Line(getWidth(), 0, 0, getHeight());
            line2.startXProperty().bind(widthProperty());
            line2.endYProperty().bind(heightProperty());

            this.getChildren().addAll(line1, line2);

            isMarked = true;
        });
    }
}

