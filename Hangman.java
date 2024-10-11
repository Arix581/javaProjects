import java.util.Random;
import java.util.Scanner;

// V0.0.4
public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Hangman!");
        
        // Constant assignment
        String[] WORDS = new String[] {"test", "words", "here", "more", "items", "string"};
        String[] PHRASES = new String[] {"This is a hangman game in java!", "This is another test string."};
        
        while (true) {
            
            playGame(WORDS, PHRASES);

            // Play again?
            System.out.println("Would you like to play again?");
            if (!scanner.nextLine().toLowerCase().startsWith("y")) {
                break;
            }
        }

        System.out.println("Goodbye!");
    }

    public static void playGame(String[] WORDS, String[] PHRASES) {
        // set the visible and secret words
        String secretWord = choosePhrase(WORDS, PHRASES);
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
                System.out.println(guess + " is not in the secret word, you lost a life.");
            }

            // Loss detection.
            if (lives < 1) { 
                // Ded :(
                victory = false;
                break;
            }

            // Win detection
            if (!visibleWord.contains("_")) {
                // No unguessed letters so you win.
                victory = true;
                break;
            }

        } // Game Loop Ends
        
        // Have different win or loss texts.
        if (victory) {
            System.out.println("The secret word was " + secretWord);
            System.out.println("You won! Good job!");
        } else {
            System.out.println("You lost. The secret word was: \"" + secretWord + "\". Better luck next time!");
        }
    }

    public static String choosePhrase(String[] words, String[] phrases) {
        String toReturn = "";
        Random random = new Random();
        if (random.nextBoolean()) {
            // Do the phrases
            //System.out.println("DEBUG [choosePhrase]: Chose to use phrases");
            toReturn = phrases[random.nextInt(phrases.length)];
        } else {
            // Do the make a phrase
            //System.out.println("DEBUG [choosePhrase]: Chose to use words");
            toReturn = generatePhrase(words);
        }

        return toReturn.toLowerCase();
    }
    
    private static String generatePhrase(String [] words) {
        String toReturn = "";
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int chosen = random.nextInt(words.length);
            toReturn += words[chosen] + " ";
        }
        return toReturn;
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
