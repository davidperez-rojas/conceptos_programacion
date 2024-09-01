import java.util.Scanner;

public class MultipleOf100Checker {
    public static void main(String[] args) {
        // Create a Scanner object to read input
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an integer
        System.out.print("Enter an integer: ");
        int number = scanner.nextInt();

        // Check if the number is a multiple of 100
        if (number % 100 == 0) {
            System.out.println("Multiple of 100.");
        } else {
            System.out.println("Not a multiple of 100.");
        }

        // Close the scanner
        scanner.close();
    }
}