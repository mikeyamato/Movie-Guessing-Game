import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class MovieGuessingGame {
    public static void main (String [] args) throws Exception {
        // computer randomly picks a movie title.
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);
//        String randomMovie = scanner.
        int numOfMovies = 0;
        int lineNum = 0;
        // initialize 10 points
        int wrongGuess = 0;

        ArrayList<String> movieOptions = new ArrayList<String>();  // ArrayList dynamically updates it's size
        while(scanner.hasNextLine()) {  // bool value
            numOfMovies++;
            String movieTitle = scanner.nextLine();  // needed in order to advance to the next line
            movieOptions.add(movieTitle);
        }
//        System.out.println(movieOptions);
        System.out.println(numOfMovies);

        int randomNum = (int)(Math.random() * numOfMovies);
        String chosenMovie = movieOptions.get(randomNum);

        System.out.println("your random index value is " + randomNum);
        System.out.println("your random movie is " + chosenMovie);
        char [] charArray = chosenMovie.toCharArray();
        String charStr = Arrays.toString(charArray);
        System.out.println(charStr);

        String chosenMovieForLoop = chosenMovie;
//        String [] chosenMovieArr = chosenMovie.split("");
//        System.out.println("WTF: " + chosenMovieArr.length);



        chosenMovie = chosenMovie.replaceAll("[A-Za-z0-9]", "_");
        System.out.println("your random movie is " + chosenMovie);




        System.out.println("Guess a letter");

        ArrayList<String> incorrectGuessedArr = new ArrayList<String>();
        ArrayList<String> allGuessedArr = new ArrayList<String>();

        while(wrongGuess < 10) {

            System.out.println("Incorrect guesses: " + wrongGuess);

            // dash out movie title and compare with letters in `allGuessedArr`
//            for(){
//
//            }

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
            System.out.println("All guesses: " + allGuessedStr.toString());

            Scanner inputLetter = new Scanner(System.in);
            String userGuess = inputLetter.nextLine();
            allGuessedArr.add(userGuess);

            String temp = userGuess;
            for (int i = 0; i<chosenMovieForLoop.length();i++){
//                System.out.println("userGuess: " + userGuess);
//                System.out.println("chosenMovieArr[i]: " + chosenMovieForLoop.charAt(i));

                if(userGuess.charAt(0) == chosenMovieForLoop.charAt(i)) {
//                    System.out.println("We got a match!");
//                    System.out.println("userGuess: " + userGuess);
//                    System.out.println("chosenMovieArr[i]: " + chosenMovieForLoop.charAt(i));
                    temp = null;
                }

            }
            if (temp != null){
                incorrectGuessedArr.add(userGuess);  // pushes to array
                wrongGuess++;
            }

        }

        // show how many letters it's made up of.
        // if a letter is indeed in the title the computer will reveal its correct position in the word, if not, you lose a point. If you lose 10 points, game over!



    }
}

