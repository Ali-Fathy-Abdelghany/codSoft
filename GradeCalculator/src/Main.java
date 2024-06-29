import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("How many subjects do you want to enter? ");
        int numberOfSubjects = readPositiveInt();
        int totalMarks = calculateTotalMarks(numberOfSubjects);
        double averagePercentage = calculateAveragePercentage(totalMarks, numberOfSubjects);

        String grade = determineGrade(averagePercentage);

        displayResults(totalMarks, averagePercentage, grade);
        in.close();

    }

    private static double calculateAveragePercentage(double totalMarks, int numberOfSubjects) {
        return totalMarks / numberOfSubjects;
    }

    private static int calculateTotalMarks(int numberOfSubjects) {
        int totalMarks = 0;
        for (int i = 1; i <= numberOfSubjects; i++) {
            totalMarks += readValidMark(i);
        }

        return totalMarks;
    }

    private static int readValidMark(int subjectNumber) {
        int mark;

        while (true) {
            try {
                System.out.println("Enter subject #" + subjectNumber + " mark out of 100");

                mark = in.nextInt();
                if (mark >= 0 && mark <= 100)
                    return mark;
                else {
                    System.out.println("Invalid mark. Enter a number in the range from 0 to 100");
                }

            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                in.next(); // Clear the invalid input
            }
        }
    }

    private static String determineGrade(double averagePercentage) {
        if (averagePercentage >= 97)
            return "A+";
        else if (averagePercentage >= 90)
            return "A";
        else if (averagePercentage >= 85)
            return "A-";
        else if (averagePercentage >= 80)
            return "B+";
        else if (averagePercentage >= 75)
            return "B";
        else if (averagePercentage >= 70)
            return "B-";
        else if (averagePercentage >= 65)
            return "C+";
        else if (averagePercentage >= 60)
            return "C";
        else if (averagePercentage >= 56)
            return "C-";
        else if (averagePercentage >= 53)
            return "D+";
        else if (averagePercentage >= 50)
            return "D";
        else
            return "F";
    }

    private static void displayResults(int totalMarks, double averagePercentage, String grade) {
        System.out.printf("Your total marks = %d%n", totalMarks);
        System.out.printf("Your average percentage = %.2f%%%n", averagePercentage);
        System.out.printf("Your grade = %s%n", grade);
    }

    private static int readPositiveInt() {
        int number;
        while (true) {
            try {

                number = in.nextInt();
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                in.next(); // Clear the invalid input
            }
        }
    }
}
