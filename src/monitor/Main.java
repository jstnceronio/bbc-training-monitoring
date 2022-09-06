package monitor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;

    Employee testEmployee = new Employee(
            "schnj",
            "Oxlong",
            "Mike",
            "password",
            "department",
            "DispoCool",
            "chargeRate"
    );

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stg = primaryStage;

        Parent root = FXMLLoader.load(getClass().getResource("monitor.fxml"));
        stg.setTitle("Login");
        stg.setScene(new Scene(root));

        switchScene("monitor.fxml", testEmployee);
        stg.show();
    }

    public void switchScene(String dest, Employee employee) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(getClass().getResource(dest).openStream());
        MonitorController monitorController = loader.getController();
        monitorController.setEmployee(employee);
        stg.getScene().setRoot(root);
    }
}
