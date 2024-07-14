package application.numbergui;

public class Difficulty {

    private DifficultyLevel difficultyLevel;

    public Difficulty() {
    }

    public Difficulty(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String hintLabelMessage() {
        return switch (this.getDifficultyLevel()) {
            case EASY -> "Enter a number between 1 and 10";
            case MEDIUM -> " Enter a number between 1 and 50";
            case HARD -> "Enter a number between 1 and 100";
        };
    }

    public int upperBoundary() {
        return switch (this.getDifficultyLevel()) {
            case EASY -> 10;
            case MEDIUM -> 50;
            case HARD -> 100;
        };
    }

    public int scoreMultiple() {
        return switch (this.getDifficultyLevel()) {
            case EASY -> 3;
            case MEDIUM -> 5;
            case HARD -> 10;
        };
    }

    public int maximumGuesses() {
        return switch (this.getDifficultyLevel()) {
            case EASY -> 3;
            case MEDIUM -> 4;
            case HARD -> 5;
        };
    }


}
