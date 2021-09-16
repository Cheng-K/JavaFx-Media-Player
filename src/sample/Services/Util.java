package sample.Services;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.util.Optional;

/*
    This class contains some utility methods such as creating a pop-up message & confirmation box
    to prompt user.
 */
public class Util {

    public static void createPopupMessage(Stage window, String message) {
        Label text = new Label(message);
        Popup popup = new Popup();
        popup.getContent().add(text);
        popup.setAutoHide(true);
        popup.show(window);
    }

    public static Optional<ButtonType> createAlert(Alert.AlertType alertType, String message,
                                                   ButtonType[] buttonTypes) {

        Alert alertWindow = new Alert(alertType, message, buttonTypes);
        return alertWindow.showAndWait();
    }
}
