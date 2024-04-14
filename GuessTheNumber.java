import java.util.Scanner;
import java.util.Random;

public class GuessTheNumber {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        final int MIN_RANGE = 1;
        final int MAX_RANGE = 100;
        final int MAX_ATTEMPTS = 10;
        
        //int generatedNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
        boolean playAgain = true;
        System.out.println("Welcome to Guess the Number Game!");
        while(playAgain){
            int generatedNumber = random.nextInt(MAX_RANGE - MIN_RANGE + 1) + MIN_RANGE;
            int score = 100;
            int attempts = 0;
            boolean guessedCorrectly = false;
            System.out.println("I have generated a number between " + MIN_RANGE + " and " + MAX_RANGE + ". Can you guess it?");
            while (attempts < MAX_ATTEMPTS && !guessedCorrectly) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                attempts++;
            
                if (userGuess == generatedNumber) {
                    guessedCorrectly = true;
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                } else if (userGuess < generatedNumber) {
                    System.out.println("Too low! Try again.");
                    score -= 10;
                } else {
                    System.out.println("Too high! Try again.");
                    score -= 10;
                }
            }
        
            if (!guessedCorrectly) {
                System.out.println("Sorry, you've run out of attempts. The correct number was: " + generatedNumber);
            }
            System.out.println("Your current score: " + score);
            System.out.print("Do you want to play again? (yes/no): ");
            String playChoice = scanner.next();
            if (playChoice.equalsIgnoreCase("yes")){
                playAgain = true;
            }else{
                playAgain = false;
            }
        }
        
        scanner.close();
    }
}