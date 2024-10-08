// Made for Kaupenjoe Minecraft Java tutorial 2023
// https://www.youtube.com/watch?v=1douKSL05W4&list=PLKGarocXCE1Egp6soRNlflWJWc44sau40&index=10
import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to the Calculator");
        Scanner scanner = new Scanner(System.in);
        
        // Set the operation and numbers.
        System.out.println("What is your desired operation? (+-*/)");
        String operation = scanner.nextLine();
        System.out.println("Number 1? ");
        int number1 = scanner.nextInt();
        System.out.println("Number 2? ");
        int number2 = scanner.nextInt();
        
        float result = 0f;
        switch (operation) {
            case "+": result = number1 + number2; break;
            case "-": result = number1 - number2; break;
            case "*": result = number1 * number2; break;
            case "/": result = number1 / number2; break;
            default:
                System.out.println(operation + " is not an operation supported by this calculator.");
                break;
        }
        System.out.println("The result is " + result);
    }
}
