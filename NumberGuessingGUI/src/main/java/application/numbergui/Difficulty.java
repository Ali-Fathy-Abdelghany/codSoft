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
    public String hintLabelMessage(){
        return switch (this.getDifficultyLevel()){
            case Easy -> "Enter a number between 1 and 10";
            case Medium -> " Enter a number between 1 and 50";
            case Hard -> "Enter a number between 1 and 100";
        };
    }
    public int upperBoundary(){
        return switch (this.getDifficultyLevel()){
            case Easy -> 10;
            case Medium -> 50;
            case Hard -> 100;
        };
    }
    public int scoreMultiple(){
        return switch (this.getDifficultyLevel()){
            case Easy -> 3;
            case Medium -> 5;
            case Hard -> 10;
        };
    }
    public int maximumGuesses(){
        return switch (this.getDifficultyLevel()){
            case Easy -> 3;
            case Medium -> 4;
            case Hard -> 5;
        };
    }


}
