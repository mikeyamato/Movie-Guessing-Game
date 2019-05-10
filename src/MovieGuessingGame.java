import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieGuessingGame {
    public static void main (String [] args) throws Exception {

        // clears screen (moves it up)
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("Movie title guessing game. Guess using any letter between A and Z.");
        System.out.println("You have 10 tries. Good luck!");


        // computer randomly picks a movie title.
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
        int numOfMovies = 0;
        // initialize 10 points
        int wrongGuess = 0;

        ArrayList<String> movieOptions = new ArrayList<String>();  // ArrayList dynamically updates it's size
        while(scanner.hasNextLine()) {  // bool value
            numOfMovies++;
            String movieTitle = scanner.nextLine();  // needed in order to advance to the next line
            movieOptions.add(movieTitle);
        }
//        System.out.println(numOfMovies);

        int randomNum = (int)(Math.random() * numOfMovies);
        String chosenMovie = movieOptions.get(randomNum);

//        System.out.println("your random index value is " + randomNum);
//        System.out.println("your random movie is: " + chosenMovie);

        String chosenMovieForLoop = chosenMovie;

        // show how many letters it's made up of.
        chosenMovie = chosenMovie.replaceAll("[A-Za-z]", "_");
        System.out.println("Guess this movie: " + chosenMovie);

        ArrayList<String> incorrectGuessedArr = new ArrayList<String>();
        ArrayList<String> allGuessedArr = new ArrayList<String>();
        StringBuilder guessedTitle = new StringBuilder(chosenMovie);
        while(wrongGuess < 10) {

            // winner winner chicken dinner
            if(guessedTitle.indexOf("_") == -1){
                System.out.println("Congrats! You guessed correctly.");
                break;
            }

            System.out.println("Incorrect guesses: " + wrongGuess);

            // keep track of incorrect letters
            StringBuilder incorrectGuessedStr = new StringBuilder();
            for (int i = 0; i < incorrectGuessedArr.size(); i++) {
                if(incorrectGuessedStr.length() == 0){
                    incorrectGuessedStr.append(incorrectGuessedArr.get(i));
                } else {
                    incorrectGuessedStr.append(", " + incorrectGuessedArr.get(i));
                }
            }
            System.out.println("Incorrect letters guessed: " + incorrectGuessedStr.toString());

            // keep track of all letters
            StringBuilder allGuessedStr = new StringBuilder();
            for (int i = 0; i < allGuessedArr.size(); i++) {
                if(allGuessedStr.length() == 0){
                    allGuessedStr.append(allGuessedArr.get(i));
                } else {
                    allGuessedStr.append(", " + allGuessedArr.get(i));
                }
            }
//            System.out.println("All guesses: " + allGuessedStr.toString());

            System.out.println("Guess a letter below!");


            Scanner inputLetter = new Scanner(System.in);
            String userGuess = inputLetter.nextLine();
            userGuess = userGuess.toLowerCase();
            allGuessedArr.add(userGuess);

            String temp = userGuess;

            if (userGuess.matches("[^A-Za-z]")){

                System.out.print("\033[H\033[2J");
                System.out.flush();
                System.out.println("Guess a character from A-Z. Try again.");
                System.out.println("Guess this movie: " + guessedTitle);
                continue;
            }

            try {
                for (int i = 0; i < chosenMovieForLoop.length(); i++) {
//                System.out.println("userGuess: " + userGuess);
//                System.out.println("chosenMovieArr[i]: " + chosenMovieForLoop.charAt(i));

                    if (userGuess.charAt(0) == chosenMovieForLoop.charAt(i)) {
//                    System.out.println("We got a match!");
//                    System.out.println("userGuess: " + userGuess);
//                    System.out.println("chosenMovieArr[i]: " + chosenMovieForLoop.charAt(i));
                        temp = null;
//                    System.out.println("word " + chosenMovieForLoop.charAt(i));
//                    System.out.println("underline " + chosenMovie.charAt(i));
//                    System.out.println("replaced with " + userGuess.charAt(0));

                        // if a letter is indeed in the title the computer will reveal its correct position in the word
                        guessedTitle.setCharAt(i, userGuess.charAt(0));
                    }
                }
                // clears screen (moves it up)
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("Guess this movie: " + guessedTitle);

                if (incorrectGuessedArr.contains(userGuess)) {
                    System.out.println("*** You already tried this character. Try again. ***");
                    continue;
                } else if (temp != null) {
                    incorrectGuessedArr.add(userGuess);  // pushes to array
                    wrongGuess++;
                }
            } catch (Exception exception){
                // clears screen (moves it up)
                System.out.print("\033[H\033[2J");
                System.out.flush();

                System.out.println("*** Hmmm...something doesn't look right. Try again. ***");
                System.out.println("Guess this movie: " + guessedTitle);
            }
        }
        // game over
        if (wrongGuess == 10){
            System.out.println("You lost. Try again.");
            System.out.println("The movie was \"" + chosenMovieForLoop + "\".");
        }
    }
}

