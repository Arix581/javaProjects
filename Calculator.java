// Made for Kaupenjoe Minecraft Java tutorial 2023
// https://www.youtube.com/watch?v=1douKSL05W4&list=PLKGarocXCE1Egp6soRNlflWJWc44sau40&index=10
import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        System.out.println("Welcome to the Calculator");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Set the operation and numbers.
            System.out.println("What is your desired operation? (+-*/)");
            String operation = scanner.nextLine();
            System.out.println("Number 1? ");
            int number1 = scanner.nextInt();
            System.out.println("Number 2? ");
            int number2 = scanner.nextInt();
            
            // Results display
            evaluate(number1, number2, operation);
            
            if (!goAgain()) {
                break;
            }
        }
    }
    
    public static float evaluate(int num1, int num2, String operation) {
        float result = 0f;
        switch (operation) {
            case "+": result = num1 + num2; break;
            case "-": result = num1 - num2; break;
            case "*": result = num1 * num2; break;
            case "/": 
                result = (num2 != 0) ? num1 / num2 : 0;
                break;
            default:
                System.out.println(operation + " is not an operation supported by this calculator.");
                break;
        }
        System.out.println("The result is " + result);
        return result;
    }
    
    public static boolean goAgain() {
        System.out.println("Would you like to go again?");
        Scanner scanner = new Scanner(System.in);
        if (scanner.nextLine().startsWith("y")) {
            return true;
        } else {
            return false;
        }
    }
}
