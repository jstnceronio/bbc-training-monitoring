package monitor;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProjectItem extends VBox {
    public Label lblTitle;

    public ProjectItem(String title) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "project-item.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        lblTitle.setText(title);
    }
}
