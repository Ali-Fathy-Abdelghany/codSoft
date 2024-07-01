import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    private BankAccount bankAccount;
    private Scanner in = new Scanner(System.in);


    public ATM(){bankAccount =new BankAccount();}
    public void initializeProgram() {
        System.out.println("WELCOME BACK!");
        initializeBalance();

        while (true) {
            displayMenu();
            int choice = readIntegerNumber("Enter your choice:");
            switch (choice) {
                case 1:
                    checkBalance();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 0:
                    exit();
                    return;

                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
                    break;
            }
        }
    }
    private void initializeBalance(){
        bankAccount.setBalance(readPositiveDoubleValue("Enter your initial balance"));
    }
    private void exit() {
        System.out.println("Thanks for using our service");
        in.close();
    }

    private static void displayMenu() {
        System.out.println("\n___________________\n");
        System.out.println("1: Check balance");
        System.out.println("2: Withdraw");
        System.out.println("3: Deposit");
        System.out.println("0: Exit");
        System.out.println("\n___________________\n");
    }


    private void withdraw() {
        double amount = readPositiveDoubleValue("Enter the amount to withdraw");
        if (amount <= bankAccount.getBalance()) {
            System.out.println("Withdrawal successful.");
            bankAccount.withdraw(amount);
        } else System.out.println("Withdrawal failed, out of balance amount");
        checkBalance();
    }

    private void deposit() {
        double amount = readPositiveDoubleValue("Enter the amount to deposit");
        System.out.println("Deposit successful.");
        bankAccount.deposit(amount);
        checkBalance();
    }

    private void checkBalance() {
        System.out.println("Your current balance is " + bankAccount.getBalance());
    }

    private int readIntegerNumber(String message) {
        int number;
        while (true) {
            try {
                System.out.println(message);
                number = in.nextInt();
                return number;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // Clear the invalid input
            }
        }
    }

    private double readPositiveDoubleValue(String message) {
        double number;
        while (true) {
            try {
                System.out.println(message);
                number = in.nextDouble();
                if (number > 0) {
                    return number;
                } else {
                    System.out.println("Invalid input. Please enter a positive number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                in.next(); // Clear the invalid input
            }
        }

    }

}