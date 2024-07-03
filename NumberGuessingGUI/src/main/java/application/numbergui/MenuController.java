package application.numbergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    private Stage stage;
    private Scene scene;
    private Parent root;


    @FXML
    private Button easy_button;

    @FXML
    private Button hard_button;

    @FXML
    private Button medium_button;

    @FXML
    void onEasyButtonClicked(ActionEvent event) throws IOException {
        Difficulty difficulty=new Difficulty();
        difficulty.setDifficultyLevel(DifficultyLevel.Easy);
        switchToGameScene(event,difficulty);
    }
    @FXML
    void onMediumButtonClicked(ActionEvent event) throws IOException {
        Difficulty difficulty=new Difficulty();
        difficulty.setDifficultyLevel(DifficultyLevel.Medium);
        switchToGameScene(event,difficulty);
    }
    @FXML
    void onHardButtonClicked(ActionEvent event) throws IOException {
        Difficulty difficulty=new Difficulty();
        difficulty.setDifficultyLevel(DifficultyLevel.Hard);
        switchToGameScene(event,difficulty);
    }
    private void switchToGameScene(ActionEvent event,Difficulty difficulty) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        root=loader.load();
        HelloController hc =loader.getController();
        hc.setDifficulty(difficulty);
        hc.startGame();

        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
