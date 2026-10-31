package gcm;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


 //controller of welcome.fxml : shows the welcome message after a successful login

public class WelcomeController {

    @FXML
    private Label welcomeLabel;

    // Puts the name of the user that logged in into the message
    public void setUserName(String name) {
        welcomeLabel.setText("Welcome, " + name + "!");
    }
}
