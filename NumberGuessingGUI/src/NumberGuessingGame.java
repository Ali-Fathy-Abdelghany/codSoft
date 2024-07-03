import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {
    static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        char playAgain;
        do {
            Random random = new Random();
            int target = random.nextInt(100) + 1;
            guessingPhase(5, target);
            System.out.println("if you want to play again enter \"y\"");
            playAgain = in.next().charAt(0);
        } while (playAgain == 'Y' || playAgain == 'y');
        System.out.println("thank you for trying our game");

    }

    public static void guessingPhase(int numberOfTrials, int target) {
        for (int i = 1; i <= numberOfTrials; i++) {
            System.out.println("Enter your #" + i + " guess");
            int guess = in.nextInt();
            if (guess == target) {
                System.out.println("Correct\nYou WIN!");
                return;
            } else if (guess > target) {
                System.out.println("too high ");

            } else {
                System.out.println("too low ");
            }

        }
        System.out.println("GAME OVER \nThe value is " + target);

    }

}
