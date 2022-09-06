package monitor;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

public class LoginController implements Initializable {
    public TextField txtUsername;
    public PasswordField txtPassword;
    public Label lblError;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        txtUsername.setText(Preferences.userRoot().get("username", "Username")); // gets the username form the preferences, if userRoot not set, returns "Username"
    }


    public void login(ActionEvent actionEvent) throws IOException {
        if (!txtUsername.getText().isEmpty()) {
            Employee employee = DBUtils.getEmployee(txtUsername.getText(), txtPassword.getText());

            if (employee != null) { // Credentials are valid
                // Remember username
                Preferences pref = Preferences.userRoot();
                String userName = txtUsername.getText();
                pref.put("username", userName);

                System.out.println("Valid bro"); //albrf cpQ1
                // Change scene
                Main m = new Main();
                m.switchScene("monitor.fxml", employee);
            } else {
                lblError.setText("Invalid login with user " + txtUsername.getText());
            }
        }
    }

    public void clear(ActionEvent actionEvent) {
        // TODO
    }
}
