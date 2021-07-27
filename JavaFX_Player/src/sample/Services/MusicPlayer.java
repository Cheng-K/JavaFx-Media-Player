package sample.Services;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.util.List;

public class MusicPlayer {
    private ObservableList<File> emptyObservableList = FXCollections.observableArrayList();
    private ListProperty<File> allSongs = new SimpleListProperty<>(emptyObservableList);
    private MediaPlayer masterPlayer;

    public void addSong(File song) {
        allSongs.add(song);
    }


    public void load(int index) {
        File targetSong = allSongs.get(index);
        Media music = new Media(targetSong.toURI().toString());
        masterPlayer = new MediaPlayer(music);
    }

    public void play() throws NullPointerException {
        try {
            masterPlayer.play();
        } catch (NullPointerException e) {
            throw new NullPointerException("No media is loaded");
        }
    }

    public void pause() {
        try {
            masterPlayer.pause();
        } catch (NullPointerException e) {
            System.out.println("No player is found");
        }
    }

    public void dispose() {
        try {
            masterPlayer.dispose();
            masterPlayer = null;
        } catch (NullPointerException e) {
            throw new NullPointerException("No player is found");
        }
    }

    public void removeMedia(int index) {
        allSongs.remove(index);
    }

    public MediaPlayer getMasterPlayer() {
        return masterPlayer;
    }

    public List<File> getAllSongs() {
        return allSongs;
    }

}
