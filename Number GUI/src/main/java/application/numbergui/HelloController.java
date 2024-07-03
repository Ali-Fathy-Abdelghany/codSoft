package application.numbergui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class HelloController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Difficulty difficulty;
    private int upperBoundary;
    private int targetNumber;
    private int guessesLeft;
    private int score = 0;
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

    @FXML
    void initialize( ) {

    }

    public void resetGame() {
        upperBoundary = difficulty.upperBoundary();
        targetNumber = new Random().nextInt(upperBoundary) + 1;
        System.out.println(targetNumber); // For testing
        guessesLeft = difficulty.maximumGuesses();
        buttonVisibality(false, true);
        updateGuessLabel();
        result_label.setText("");
        result_label.setTextFill(Paint.valueOf("black"));
        score_label.setText("Score: " + score);
        hint_label.setText(difficulty.hintLabelMessage());
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

    void finishGame() {
        buttonVisibality(true, false);
        score_label.setTextFill(score >= 1000 ? Paint.valueOf("gold") : Paint.valueOf("black"));
        score_label.setText("Score: " + score);
    }

    @FXML
    void onSubmitButtonClicked(ActionEvent event) {
        try {
            int guess = Integer.parseInt(guess_tf.getText().trim());

            if (!inRange(guess))
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

    private boolean inRange(int guess) {
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
        finishGame();
    }
    void win() {
        result_label.setText("Correct!");
        result_label.setTextFill(Paint.valueOf("green"));
        score += calculateScore();
        finishGame();
    }

    private int calculateScore() {
        return switch (guessesLeft) {
            case 5 -> 100 * difficulty.scoreMultiple();
            case 4 -> 50 * difficulty.scoreMultiple();
            case 3 -> 30 * difficulty.scoreMultiple();
            case 2 -> 20 * difficulty.scoreMultiple();
            case 1 -> 10 * difficulty.scoreMultiple();
            default -> 0;
        };
    }



    @FXML
    void onResetButtonClicked(ActionEvent event) {
        resetGame();
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    @FXML
    void onChangeDiffButtonClicked(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
