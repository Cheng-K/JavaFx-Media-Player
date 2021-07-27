package sample;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;
import sample.Services.Util;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RemoveDialogController implements Initializable {
    private Stage window;
    private List<File> allSongs;
    @FXML
    private ListView<File> playlist;


    public RemoveDialogController(List<File> allSongs, Stage window) {
        this.allSongs = allSongs;
        this.window = window;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        playlist.setItems((ObservableList<File>) allSongs);
        playlist.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    public void onCancelRequest() {
        window.close();
    }

    public void onRemoveRequest() {
        var allSelected = playlist.getSelectionModel().getSelectedItems();
        var response =
                Util.createAlert(Alert.AlertType.CONFIRMATION, "Are you sure you want to remove all selected media from playlist?\n " +
                        "This will not affect the current playback.", new ButtonType[]{ButtonType.YES, ButtonType.NO});
        if (response.isPresent() && response.get().equals(ButtonType.YES)) {
            allSongs.removeAll(allSelected);
            window.close();
        }
    }
}
