import java.util.Scanner;

// V0.0.3
public class Hangman {
    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        
        // set the visible word to all underscores
        String secretWord = "This is a hangman game in java!".toLowerCase();
        String visibleWord = secretWord.replaceAll("(?i)[qwertyuiopasdfghjklzxcvbnm]", "_"); 
        
        // Set up basic variables
        int lives = 6;
        String guesses = "";
        boolean victory = false;
        
        while (true) {
            // Display the board
            System.out.println("You have " + lives + " lives.");
            System.out.println(visibleWord);
            
            // Get a guess
            String guess = getGuess(guesses);
            guesses += guess;

            // Check if the guess is in the world.
            if (secretWord.contains(guess)) { // Is in the secret word
                // Add to the visible word
                visibleWord = updateVisibleWord(secretWord, visibleWord, guess);
            } else { // Is not in the secret word
                lives--;
                System.out.println(guess + " is not in the secret word.");
            }

            // Loss detection.
            if (lives < 1) { 
                victory = false;
                break;
            } else {
                System.out.println("You now have " + lives + " lives remaining.");
            }

            // Win detection
            if (!visibleWord.contains("_")) {
                // No unguessed letters
                // so you win.
                victory = true;
                break;
            }

        } // Game Loop Ends
        
        // Have different win or loss texts.
        if (victory) {
            System.out.println("You won! Good job!");
        } else {
            System.out.println("You lost. The secret word was: \"" + secretWord + "\". Better luck next time!");
        }

        // Play again?
    }

    private static String updateVisibleWord(String secretWord, String visibleWord, String guess) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess.charAt(0)) {
                visibleWord = visibleWord.substring(0,i) + guess + visibleWord.substring(i + 1 , visibleWord.length());
            }
        }
        return visibleWord;
    }

    public static String getGuess(String guesses) {
        // Take in the guesses and then output the players guess
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // Get a guess
            String guess = "";
            while (true) {
                System.out.println("Make a guess!");
                guess = scanner.nextLine().toLowerCase();

                // Ensure it is not whitespace.
                if (guess.length() == 0 || guess.startsWith(" ")) {
                    System.out.println("Guess cannot be whitespace.");
                    continue;
                } else {
                    break;
                }
            }
            // Make it just one character (as a string, not a char)
            guess = guess.substring(0, 1);
            
            // Repeat prevention
            if (guesses.contains(guess))
            {
                System.out.println("You have already guessed that.");
                continue;
            } else {
                // Looks like the guess is valid!
                return guess;
            }
        }
    }
}
