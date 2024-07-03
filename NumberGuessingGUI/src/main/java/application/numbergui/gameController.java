package application.numbergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class gameController {
    private static int score = 0;
    private Alert alert;
    private Difficulty difficulty;
    private int upperBoundary;
    private int targetNumber;
    private int guessesLeft;
    @FXML
    private Label score_label;
    @FXML
    private Label hint_label;
    @FXML
    private Button reset_button;

    @FXML
    private TextField guess_tf;

    @FXML
    private Label guesses_label;

    @FXML
    private Label result_label;

    @FXML
    private Button submit_button;
    @FXML
    private Button changeDiff_button;

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void startGame() {
        upperBoundary = getDifficulty().upperBoundary();
        targetNumber = new Random().nextInt(upperBoundary) + 1;
        System.out.println(targetNumber); // For testing
        guessesLeft = getDifficulty().maximumGuesses();
        buttonVisibality(false, true);
        updateGuessLabel();
        result_label.setText("");
        result_label.setTextFill(Paint.valueOf("black"));
        score_label.setText("Score: " + score);
        hint_label.setText(getDifficulty().hintLabelMessage());
    }

    void finishGame() {
        buttonVisibality(true, false);
        score_label.setText("Score: " + score);
    }

    private void buttonVisibality(boolean value, boolean value1) {
        submit_button.setDisable(value);
        submit_button.setVisible(value1);

        reset_button.setDisable(value1);
        reset_button.setVisible(value);

        changeDiff_button.setDisable(value1);
        changeDiff_button.setVisible(value);
    }

    private void updateGuessLabel() {
        guesses_label.setTextFill(Paint.valueOf("black"));
        guesses_label.setText(guessesLeft + " guesses left");
        if (guessesLeft < 2) guesses_label.setTextFill(Paint.valueOf("red"));
    }


    private boolean isGuessInRange(int guess) {
        if (guess > upperBoundary || guess < 1) {
            result_label.setText("Please enter number in range");
            return false;
        }
        return true;
    }

    private void handleIncorrectGuess(int guess) {
        if (guessesLeft > 1) {
            result_label.setText(guess > targetNumber ? "Too high" : "Too low");
        } else {
            lose();
        }
    }

    void lose() {
        result_label.setTextFill(Paint.valueOf("red"));
        result_label.setText("You lost! The number was " + targetNumber);
        //showing alert
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("YOU LOST!");
        alert.setHeaderText("Hard Luck! >_<");
        alert.setContentText("The Number Was " + targetNumber + ".");
        alert.showAndWait();
        finishGame();
    }

    void win() {
        result_label.setText("Correct!");
        result_label.setTextFill(Paint.valueOf("#078c50"));
        score += calculateScore();
        //showing alert
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("YOU WON!");
        alert.setHeaderText("CONGRATS! ^_^");
        alert.setContentText("You Guessed Correctly.");
        alert.showAndWait();

        finishGame();
    }

    private int calculateScore() {
        return switch (guessesLeft) {
            case 5 -> 100 * getDifficulty().scoreMultiple();
            case 4 -> 50 * getDifficulty().scoreMultiple();
            case 3 -> 30 * getDifficulty().scoreMultiple();
            case 2 -> 20 * getDifficulty().scoreMultiple();
            case 1 -> 10 * getDifficulty().scoreMultiple();
            default -> 0;
        };
    }

    @FXML
    void onSubmitButtonClicked(ActionEvent event) {
        try {

            int guess = Integer.parseInt(guess_tf.getText().trim());

            if (!isGuessInRange(guess))
                return;

            if (guess == targetNumber)
                win();
            else
                handleIncorrectGuess(guess);


            guessesLeft--;
            updateGuessLabel();
        } catch (NumberFormatException e) {
            result_label.setText("Please enter only numbers");
        }
    }

    @FXML
    void onResetButtonClicked(ActionEvent event) {
        startGame();
    }


    @FXML
    void onChangeDiffButtonClicked(ActionEvent event) throws IOException {
        //load menu scene
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        Parent root = loader.load();
        //Change scenes
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
