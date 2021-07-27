package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.lang.reflect.InvocationTargetException;


public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MusicPlayer.fxml"));
        loader.setControllerFactory(c -> {
            try {
                return c.getConstructor(Stage.class).newInstance(primaryStage);
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        });

        Parent root = loader.load();
        primaryStage.setTitle("Kei's Player");
        primaryStage.getIcons().add(new Image(getClass().getResource("/title-icon.png").toExternalForm()));
        primaryStage.setScene(new Scene(root, 1900, 900));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
