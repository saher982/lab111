package gcm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;


  //Main class of the GCM login app.
  //Loads the valid users from users.txt and opens the login screen.

public class GcmApp extends Application {

     private static final String USERS_FILE = "users.txt";

      @Override
       public void start(Stage stage) throws Exception {
        // list of the valid users from the input file
        ArrayList<User> users;
        try {
             users = UsersLoader.loadValidUsers(USERS_FILE);
         } catch (FileNotFoundException e) {
              System.err.println("Input file not found: " + USERS_FILE);
               users = new ArrayList<>(); // open the window anyway, every login will fail
           }

         // open the login screen and give its controller the users list
        FXMLLoader loader = new FXMLLoader(GcmApp.class.getResource("login.fxml"));
         Scene scene = new Scene(loader.load());
           LoginController controller = loader.getController();
             controller.setUsers(users);

        // clicking x closes the window and ends program
        stage.setOnCloseRequest(event -> Platform.exit());

        stage.setTitle("Users Login");
          stage.setScene(scene);
          stage.show();
      }

    public static void main(String[] args) {
         launch(args);
    }
}
