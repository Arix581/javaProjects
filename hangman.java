import java.util.Scanner;

// V0.0.2 
public class hangman {
    public static void main(String[] args) {
        /* 
         * Things we have to do:
         * Fix bug with the input system
         */
        System.out.println("Hello World");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        
        // set the visible word to all underscores
        String secretWord = "This is a test string.".toLowerCase();
        String visibleWord = secretWord.replaceAll("(?i)[qwertyuiopasdfghjklzxcvbnm]", "_"); 
        
        int lives = 3;
        String guesses = "";
        boolean gameDone = false;
        boolean victory = false;
        
        while (!gameDone) {
            // Display the board
            System.out.println(visibleWord);
            
            // Make a guess
            System.out.println("Make a guess! (You have " + lives + " lives)");
            
            // BUG! If you submit just an enter, it breaks.
            // Just check if the player submitted a string of length
            // 0 and then make them reguess before using the guess.
            String guess = scanner.nextLine().substring(0,1);
            
            // Repeat prevention
            if (guesses.contains(guess))
            {
                System.out.println("You have already guessed that.");
                continue;
            } else {
                guesses += guess;
            }

            // Check if the guess is in the world.
            if (secretWord.contains(guess)) { // Is in the secret word
                // Add to the visible word
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess.charAt(0)) {
                        visibleWord = visibleWord.substring(0,i) + guess + visibleWord.substring(i + 1 , visibleWord.length());
                    }
                }
            } else { // Is not in the secret word
                lives--;
                System.out.println(guess + " is not in the secret word.");
                if (lives < 1) { // Die
                    victory = false;
                    break;
                } else {
                    System.out.println("You have " + lives + " lives remaining.");
                }
            }
        } // Game Loop Ends
        
        // Have different win or loss texts.
    }
}
