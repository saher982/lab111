// JavaFX modules the app needs, and access for FXML to our controllers
module gcm {
    requires javafx.controls;
    requires javafx.fxml;

    opens gcm to javafx.fxml;
    exports gcm;
}
