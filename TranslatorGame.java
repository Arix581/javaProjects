import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Random;

public class TranslatorGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        String letters = "abcdefghijklmnopqrstuvwxyz";

        boolean continuePlaying = true;
        /*
         * TODO
         * Modes:
         *   Endless
         *   Lives
         *   Practice (allows you to do one set)
         */
        while (continuePlaying) {
            System.out.println();
            System.out.println("Welcome to Translator! This is to help you with A-Z 1-26 Codes! What mode would you like?");
            System.out.println("There are three modes: Endless, Lives, and Practice, or you could quit.");
            String mode = scanner.nextLine().toLowerCase();
            switch (String.valueOf(mode.charAt(0))) {
                case "e":
                    // Endless Mode
                    endlessMode(scanner, letters);
                    break;
                case "l":
                    // Lives Mode
                    livesMode(scanner, letters, 3);
                    break;
                case "p":
                    // Practice Mode
                    practiceMode(scanner, letters);
                    break;
                case "q":
                    continuePlaying = false;
                    break;
                default:
                    System.out.println("Mode load failed. Please try again.");
                    break;
            }
            System.out.println("Returning to main menu...");
        }
    }

    private static int endlessMode(Scanner scanner, String letters) {
        boolean usingLettters = getUsingLetters(scanner);
        int points = 0;
        while (true) {
            if (!(usingLettters ? guessFromRandomLetter(scanner, letters) : guessFromRandomNumber(scanner, letters))) {
                break;
            }
            else 
            {
                points++;
                System.out.println("You got a point!");
            }
        }
        System.out.println("Game over. You scored " + points + " points.");
        return points;
    }

    private static int livesMode(Scanner scanner, String letters, int lives) {
        boolean usingLettters = getUsingLetters(scanner);
        int points = 0;
        for (int i = 1; i <= lives; i++) {
            while (true) {
                if (!(usingLettters ? guessFromRandomLetter(scanner, letters) : guessFromRandomNumber(scanner, letters))) {
                    System.out.println("You have " + (lives - i) + " lives remaining");
                    break;
                }
                else 
                {
                    points++;
                    System.out.println("You got a point!");
                }
            }
        }
        System.out.println("Game over. You scored " + points + " points.");
        return points;
    }
    
    private static void practiceMode(Scanner scanner, String letters) {
        System.out.println("Prints only first character");
        // Get desired string
        System.out.println("Welcome to practice mode. Input all characters you want to practice.");
        String practice = scanner.nextLine().trim().toLowerCase();
        char[] chars = practice.toCharArray();
        List<char[]> practiceList = Arrays.asList(chars);

        // Get letters or numbers
        boolean usingLettters = getUsingLetters(scanner);

        // Get reps
        System.out.println("How many repetitions would you like?");
        int repetitions = scanner.nextInt();
        scanner.nextLine();

        // Make a mistakes string and have concatinated to the end all the mistakes
        List<char[]> mistakes = new ArrayList<>();
        
        // Shuffle and dispense all the letters.
        for (int i = 0; i < repetitions; i++) {
            Collections.shuffle(practiceList);
            for (int j = 0; j < practiceList.size() + 1; j++) {
                boolean correct = false;
                if (usingLettters) {
                    correct = guessFromSetLetter(scanner, letters, String.valueOf(practiceList.get(j))); 
                } else {
                    correct = guessFromSetNumber(scanner, letters, String.valueOf(practiceList.get(j)));
                }
                if (!correct) {
                    mistakes.add(practiceList.get(j));
                }
            }
        }

        // Ending mistake handling
        if (mistakes.size() == 0) { // No mistakes
            System.out.println("You made no mistakes! Good job!");
        } else { // Yes mistakes
            System.out.println("You made " + mistakes.size() + " mistakes. Here they are: ");
            for (char[] character : mistakes) {
                System.out.print(character);
            }
            System.out.println();
        }

    }

    private static boolean getUsingLetters(Scanner scanner) {
        System.out.println("Would you like to go to letters or numbers");
        return !scanner.nextLine().toLowerCase().startsWith("l");
    }

    private static boolean guessFromRandomNumber(Scanner scanner, String letters) {
        Random rand = new Random();
        int target = rand.nextInt(letters.length());
        System.out.println("What does number " + (target + 1) + " mean: ");

        if (scanner.nextLine().startsWith(String.valueOf(letters.charAt(target)))) {
            System.out.println("Correct!");
            return true;
        }
        else {
            System.out.println("Incorrect.");
            return false;
        }
    }

    private static boolean guessFromRandomLetter(Scanner scanner, String letters) {
        Random rand = new Random();
        int target = rand.nextInt(letters.length());
        System.out.println("What does letter " + (String.valueOf(letters.charAt(target))) + " mean: ");

        if (scanner.nextInt() == target + 1) {
            System.out.println("Correct!");
            scanner.nextLine();
            return true;
        }
        else {
            System.out.println("Incorrect.");
            scanner.nextLine();
            return false;
        }
    }

    private static boolean guessFromSetNumber(Scanner scanner, String letters, String targetString) {
        int target = letters.indexOf(targetString);
        System.out.println("What does number " + (target + 1) + " mean: ");

        if (scanner.nextLine().startsWith(String.valueOf(letters.charAt(target)))) {
            System.out.println("Correct!");
            return true;
        }
        else {
            System.out.println("Incorrect.");
            return false;
        }
    }

    private static boolean guessFromSetLetter(Scanner scanner, String letters, String targetString) {
        int target = letters.indexOf(targetString);
        System.out.println("What does letter " + (String.valueOf(letters.charAt(target))) + " mean: ");

        if (scanner.nextInt() == target + 1) {
            System.out.println("Correct!");
            scanner.nextLine();
            return true;
        }
        else {
            System.out.println("Incorrect.");
            scanner.nextLine();
            return false;
        }
    }
}
