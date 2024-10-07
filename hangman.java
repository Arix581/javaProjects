import java.util.Scanner;

public class hangman {
    public static void main(String[] args) {
        /* 
         * Things we have to do:
         * Get a guess from the player
         * Add the guess to a list of guesses
         * if the guess isnt in the word and not in the list of guesses
         *   remove a life.
         * For each character of the word
         *   See if the letter has been guessed
         *   if it has, it will be displayed
         *   else it will be replaced by an underscore
         * print out the guess result
         */
        System.out.println("Hello World");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        int lives = 3;
        String secretWord = "test word";
        String visibleWord = "";
        String guesses = "";
        
        boolean gameDone = false;
        boolean victory = false;
        while (!gameDone) {
            // Make a guess
            System.out.println("Make a guess! (You have " + lives + " lives)");
            String guess = scanner.nextLine().substring(0,1);
            if (guesses.contains(guess))
            {
                System.out.println("You have already guessed that.");
                continue;
            }

            // Check if the guess is in the world.
            if (secretWord.contains(guess)) { // Is in the secret word
                // Add to the visible word
            } else { // Is not in the secret word
                // Remove life and check for failure.
            }

            // Display the guessed words thus far.
        }
    }
}
