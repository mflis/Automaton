package pl.agh.edu.Automaton;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * launcher class for JavaFX application
 */
public class Main extends Application {

    /**
     * launches  JavaFX application
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException if FXML loader won't find .fxml file
     *                     containing visual layout of application
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        AnchorPane page = FXMLLoader.load(Main.class.getResource("/main.fxml"));
        Scene scene = new Scene(page);
        primaryStage.setScene(scene);


        primaryStage.setResizable(false);
        primaryStage.setTitle("Automatons");
        primaryStage.show();

    }
}

