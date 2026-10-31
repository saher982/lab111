package gcm;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


 //controller of login.fxml , checks the username and password when login is clicked.

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Label errorLabel;

    // the valid users, set by GcmApp after the screen is loaded
    private ArrayList<User> users = new ArrayList<>();

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }


     // runs when the login button is clicked (or enter).
     // if a user with these details exists, switch to the welcome screen,
     // otherwise show an error message inside the window.

    @FXML
    void onLoginClick(ActionEvent event) throws IOException {
        String name = username.getText().trim();
        String pass = password.getText();

        User found = findUser(name, pass);
        if (found == null) {
            errorLabel.setText("user or password do not match");
            password.clear();
            return;
        }

        // load the welcome screen (a new fxml) into the same window
        FXMLLoader loader = new FXMLLoader(GcmApp.class.getResource("welcome.fxml"));
        Scene welcomeScene = new Scene(loader.load());
        WelcomeController controller = loader.getController();
        controller.setUserName(found.getName());

        Stage stage = (Stage) username.getScene().getWindow();
        stage.setTitle("Welcome");
        stage.setScene(welcomeScene);
    }

    // Returns the user with this username and password, or null if there is none
    private User findUser(String name, String pass) {
         for (User u : users) {
               if (u.getName().equals(name) && u.getPassword().equals(pass)) {
                   return u;
            }
         }
          return null;
      }
   }
