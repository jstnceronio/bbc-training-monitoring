package monitor;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ProjectController extends VBox {
    public ProgressBar pbProgress;
    public ProgressBar pbBudget;
    public Label lblTitle;

    public ProjectController(String title, double progress, double plannedHours, double plannedExpenses) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(
                "project.fxml"));
        fxmlLoader.setRoot(this);
        fxmlLoader.setController(this);

        try {
            fxmlLoader.load();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        lblTitle.setText(title);
        this.pbProgress.setProgress(progress);
    }

    public void updateProgress(ActionEvent actionEvent) {
        pbProgress.setProgress(
                pbProgress.getProgress() + 0.01
        );
        System.out.println("Updated to " + pbProgress.getProgress());
    }

    public void showDetails(ActionEvent actionEvent) {
    }
}
