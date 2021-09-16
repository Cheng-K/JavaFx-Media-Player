package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.Services.MusicPlayer;
import sample.Services.Util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/*
    Controller class focus on the layout of the application
 */
public class Controller implements Initializable {
    private Stage window;
    private ImageView playIcon;
    private ImageView pauseIcon;
    private MusicPlayer player;
    private ListChangeListener<File> selectedSongsListener;
    private ChangeListener<Number> selectedIndexListener;
    @FXML
    private SplitPane root;
    @FXML
    private VBox playlistPane;
    @FXML
    private Button playButton;
    @FXML
    private Slider durationSlider;
    @FXML
    private Label currentTime;
    @FXML
    private Label musicTitleLabel;
    @FXML
    private ListView<File> playlist;
    @FXML
    private Label endTime;
    @FXML
    private ToggleButton repeatButton;
    @FXML
    private MediaView mediaView;
    @FXML
    private ImageView minMaxPlaylistButton;


    public Controller(Stage window) {
        this.window = window;
        player = new MusicPlayer();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image playImage = new Image(getClass().getResource("/play-icon.png").toExternalForm());
        playIcon = new ImageView(playImage);
        playIcon.setFitHeight(30);
        playIcon.setFitWidth(30);

        Image pauseImage = new Image(getClass().getResource("/pause-icon.png").toExternalForm());
        pauseIcon = new ImageView(pauseImage);
        pauseIcon.setFitHeight(30);
        pauseIcon.setFitWidth(30);

        // Listener to detect whether user click on another music at the playlist
        selectedIndexListener = new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                MediaPlayer.Status currentStatus;
                try {
                    currentStatus = player.getMasterPlayer().getStatus();
                } catch (NullPointerException e) {
                    currentStatus = null;
                }
                // if music player is currently playing media, prompt the user to change music
                if (currentStatus != null) {

                    Optional<ButtonType> response = Util.createAlert(Alert.AlertType.CONFIRMATION,
                            "Do you wish to proceed to switch media? Your current playback will be lost.",
                            new ButtonType[]{ButtonType.YES, ButtonType.NO});
                    if (response.isEmpty() || response.get().equals(ButtonType.NO)) {
                        playlist.getSelectionModel().selectedIndexProperty().removeListener(this);
                        playlist.getSelectionModel().select((int) number);
                        playlist.getSelectionModel().selectedIndexProperty().addListener(this);
                        return;
                    } else
                        player.dispose();
                }
                loadMusicRequest((int) t1);
            }
        };

        playButton.setGraphic(playIcon);
        playlist.setItems((ObservableList<File>) player.getAllSongs());

        playlist.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        playlist.getSelectionModel().selectedIndexProperty().addListener(selectedIndexListener);
    }


    public void playPauseMusicRequest() {
        MediaPlayer.Status currentStatus;
        try {
            currentStatus = player.getMasterPlayer().getStatus();
        } catch (NullPointerException e) {
            currentStatus = null;
        }

        if (currentStatus == null) {
            Util.createPopupMessage(window, "No media is loaded from playlist");
        } else if (currentStatus.equals(MediaPlayer.Status.PLAYING))
            player.pause();
        else
            player.play();

    }

    // Change the play pause icon based on player status
    public void mediaStatusListener(MediaPlayer.Status newStatus) {
        if (newStatus.equals(MediaPlayer.Status.PLAYING))
            playButton.setGraphic(pauseIcon);
        else if (newStatus.equals(MediaPlayer.Status.HALTED))
            Util.createPopupMessage(window, "ERROR : Critical Error Occurred. Please select and load music again");
        else
            playButton.setGraphic(playIcon);
    }

    // Change the repeat icon based on player status
    public void repeatButtonGraphicListener() {
        MediaPlayer.Status currentStatus;
        try {
            currentStatus = player.getMasterPlayer().getStatus();
        } catch (NullPointerException e) {
            currentStatus = null;
        }

        if (currentStatus == null) {
            repeatButton.setSelected(false);
            Util.createPopupMessage(window, "No media is loaded from playlist");
            return;
        }

        Image image;
        ImageView icon;
        if (repeatButton.isSelected()) {
            image = new Image(getClass().getResource("/repeat2-icon.png").toExternalForm());
        } else {
            image = new Image(getClass().getResource("/repeat-icon.png").toExternalForm());
        }

        icon = new ImageView(image);
        icon.setFitHeight(30);
        icon.setFitWidth(30);
        repeatButton.setGraphic(icon);
    }

    // Minimize or Expand the playlist located at the side
    public void minMaxPlaylistRequest() {
        Image currentImage = minMaxPlaylistButton.getImage();
        Image minimizeImage = new Image(getClass().getResource("/minimize-icon.png").toExternalForm());
        Image maximizeImage = new Image(getClass().getResource("/maximize-icon.png").toExternalForm());
        if (currentImage.getUrl().equals(minimizeImage.getUrl())) {
            root.getItems().remove(playlistPane);
            root.setDividerPosition(0, 0);
            minMaxPlaylistButton.setImage(maximizeImage);
        } else {
            root.getItems().add(0, playlistPane);
            root.setDividerPosition(0, 0.245);
            minMaxPlaylistButton.setImage(minimizeImage);
        }

    }

    // Open file explorer to let user choose media file
    public void openFileHandler() throws NullPointerException {
        Stage test = new Stage();
        test.initModality(Modality.APPLICATION_MODAL);
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select a song");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Supported Files (*.wav,*.mp3, *.mp4, *.flv, *.aiff)", "*.wav", "*.mp3", "*.mp4", "*.flv", "*.aiff"),
                new FileChooser.ExtensionFilter("Wav files (*.wav)", "*.wav"),
                new FileChooser.ExtensionFilter("MP3 files (*.mp3)", "*.mp3"),
                new FileChooser.ExtensionFilter("Wav files (*.mp4)", "*.mp4"),
                new FileChooser.ExtensionFilter("FLV files (*.flv)", "*.flv"),
                new FileChooser.ExtensionFilter("AIFF files (*.aiff)", "*.aiff")
        );

        File file = fileChooser.showOpenDialog(window);
        if (file == null) {
            Util.createPopupMessage(window, "No file is selected.");
            return;
        }
        player.addSong(file);
    }

    public void removeMediaRequest() throws IOException {
        playlist.getSelectionModel().selectedIndexProperty().removeListener(selectedIndexListener);
        // Instantiate a new dialog that allows user to remove media from playlist
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RemoveDialog.fxml"));
        Stage dialogWindow = new Stage();
        loader.setControllerFactory((objectClass) -> {
            try {
                return objectClass.getConstructor(List.class, Stage.class).newInstance(player.getAllSongs(), dialogWindow);
            } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                return null;
            }
        });
        Parent parent = loader.load();
        dialogWindow.setScene(new Scene(parent));
        dialogWindow.initModality(Modality.APPLICATION_MODAL);
        dialogWindow.showAndWait();
        playlist.getSelectionModel().clearSelection();
        playlist.getSelectionModel().selectedIndexProperty().addListener(selectedIndexListener);
    }

    public void nextMusicRequest() {
        if (playlist.getSelectionModel().getSelectedIndex() + 1 != playlist.getItems().size())
            playlist.getSelectionModel().selectNext();
        else
            playlist.getSelectionModel().selectFirst();
    }

    public void previousMusicRequest() {
        if (playlist.getSelectionModel().getSelectedIndex() - 1 > -1)
            playlist.getSelectionModel().selectPrevious();
        else
            playlist.getSelectionModel().selectLast();
    }

    //List view handlers functions
    public void loadMusicRequest(int selectedIndex) {

        if (selectedIndex != -1) {
            // Start to load & set up the new music
            player.load(selectedIndex);
            musicTitleLabel.setText(playlist.getSelectionModel().getSelectedItem().getName());
            player.getMasterPlayer().setOnReady(() -> {
                mediaView.setMediaPlayer(player.getMasterPlayer());
                setUpDurationSlider();
                bindTimeWithSlider();
                endTime.setText(getEndTime());
            });
            player.getMasterPlayer().setOnEndOfMedia(() -> {
                player.getMasterPlayer().stop();
                player.getMasterPlayer().seek(Duration.ZERO);
                if (repeatButton.isSelected())
                    player.play();
            });

            player.getMasterPlayer().statusProperty().addListener((container, oldStatus, newStatus) -> mediaStatusListener(newStatus));
            player.getMasterPlayer().setAutoPlay(true);
        } else {
            System.out.println("No music is selected");
        }
    }

    // Duration slider essential functions (handling fast-forward & playback)
    public void setUpDurationSlider() {
        durationSlider.setMax(player.getMasterPlayer().getTotalDuration().toSeconds());
        player.getMasterPlayer().currentTimeProperty().addListener((container, oldValue, newValue) -> {
            if (!durationSlider.isValueChanging())
                durationSlider.setValue(newValue.toSeconds());
        });
        durationSlider.valueChangingProperty().addListener((value, wasChanging, isChangingNow) -> {
            if (!isChangingNow)
                player.getMasterPlayer().seek(Duration.seconds(durationSlider.getValue()));
        });

        durationSlider.valueProperty().addListener((container, oldValue, newValue) -> {
                    if (!durationSlider.isValueChanging()) {
                        // Perform rounding to avoid micro-changes caused by player time property change listener
                        double currentPlaybackTime = player.getMasterPlayer().getCurrentTime().toSeconds();
                        if (Math.abs(currentPlaybackTime - newValue.doubleValue()) > 0.5)
                            player.getMasterPlayer().seek(Duration.seconds(newValue.doubleValue()));
                    }
                }
        );
    }
    // Binding that moves the slider automatically as the media plays
    public void bindTimeWithSlider() {
        currentTime.textProperty().bind(
                Bindings.createStringBinding(() -> {
                    double currentSeconds = durationSlider.getValue();
                    return String.format("%d:%02d:%02d%n", (int) (currentSeconds / 3600), (int) (currentSeconds / 60) % 60, (int) (currentSeconds % 60));
                }, durationSlider.valueProperty()));
    }

    public String getEndTime() {
        Duration totalTime = player.getMasterPlayer().getTotalDuration();
        return String.format("%.0f:%02d:%02d%n", totalTime.toHours(), (int) (totalTime.toMinutes()) % 60, (int) (totalTime.toSeconds()) % 60);
    }

}













