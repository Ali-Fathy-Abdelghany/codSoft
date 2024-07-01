import java.util.InputMismatchException;
import java.util.Scanner;

public class ATM {
    // color codes for coloring the output
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_CYAN = "\u001B[36m";
    private BankAccount bankAccount;
    private Scanner in = new Scanner(System.in);


    public ATM(){bankAccount =new BankAccount();}
    public void initializeProgram() {
        System.out.println(ANSI_CYAN+"WELCOME BACK!"+ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Invalid choice. Please enter a valid option." + ANSI_RESET);
                    break;
            }
        }
    }
    private void initializeBalance(){
        bankAccount.setBalance(readPositiveDoubleValue("Enter your initial balance"));
    }
    private void exit() {
        System.out.println(ANSI_GREEN + "Thanks for using our service" + ANSI_RESET);
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
            System.out.println(ANSI_GREEN + "Withdrawal successful." + ANSI_RESET);
            bankAccount.withdraw(amount);
        } else
            System.out.println(ANSI_RED + "Withdrawal failed, out of balance amount" + ANSI_RESET);
        checkBalance();
    }

    private void deposit() {
        double amount = readPositiveDoubleValue("Enter the amount to deposit");
        System.out.println(ANSI_GREEN + "Deposit successful." + ANSI_RESET);
        bankAccount.deposit(amount);
        checkBalance();
    }

    private void checkBalance() {
        System.out.println("Your current balance is " +  ANSI_CYAN + bankAccount.getBalance() + ANSI_RESET);
    }

    private int readIntegerNumber(String message) {
        int number;
        while (true) {
            try {
                System.out.println(message);
                number = in.nextInt();
                return number;
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
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
                    System.out.println(ANSI_RED + "Invalid input. Please enter a positive number." + ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(ANSI_RED + "Invalid input. Please enter a number." + ANSI_RESET);
                in.next(); // Clear the invalid input
            }
        }

    }

}