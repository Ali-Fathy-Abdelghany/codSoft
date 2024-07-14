package application.numbergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {


    @FXML
    void onEasyButtonClicked(ActionEvent event) throws IOException {
        switchToGameScene(event, new Difficulty(DifficultyLevel.EASY));
    }

    @FXML
    void onMediumButtonClicked(ActionEvent event) throws IOException {
        switchToGameScene(event, new Difficulty(DifficultyLevel.MEDIUM));
    }

    @FXML
    void onHardButtonClicked(ActionEvent event) throws IOException {
        switchToGameScene(event, new Difficulty(DifficultyLevel.HARD));
    }

    private void switchToGameScene(ActionEvent event, Difficulty difficulty) throws IOException {
        //Load Game scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        //transferring data
        gameController hc = loader.getController();
        hc.setDifficulty(difficulty);
        hc.startGame();

        //Change scenes
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
