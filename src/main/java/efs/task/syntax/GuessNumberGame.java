package efs.task.syntax;
import java.util.Random;
import java.util.Scanner;

import static efs.task.syntax.UsefulConstants.*;


public class GuessNumberGame {
    public int M;
    //Do not modify main method
    public static void main(String[] args) {
        try {
            GuessNumberGame game = new GuessNumberGame(args.length > 0 ? args[0] : "");
            game.play();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public GuessNumberGame(String argument){
        //TODO: Implement the constructor
        try {
            M = Integer.parseInt(argument);
        } catch (NumberFormatException e) {
            System.out.println(WRONG_ARGUMENT);
            throw new IllegalArgumentException(e);
        }
        if( M < 1 || M > MAX_UPPER_BOUND){
            System.out.println(WRONG_ARGUMENT);
            throw new IllegalArgumentException();
        }

    }

    public void play() {
        //TODO: Implement the method that executes the game session
        System.out.println("Zagrajmy. Zgadnij liczbę z zakresu <1,"+M+">");
        boolean guessed = false;
        int attemps = 0;
        Scanner scanner = new Scanner(System.in);
        int generatedNumber = generateNumber(M);
        int guessedNumber;
        int L = (int) Math.abs(Math.floor(Math.log(M)/(Math.log(2))+1));

        for(int i = 1; i <= L; i++){
            progressBar(L, i);
            System.out.println(GIVE_ME+" liczbę :");
            String input = scanner.nextLine();
            try {
                guessedNumber = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Hmm, '"+input+"' to "+NOT_A_NUMBER);
                continue;
            }


                if(generatedNumber > guessedNumber){
                    System.out.println("To "+TO_LESS);
                }else if(generatedNumber < guessedNumber){
                    System.out.println("To "+TO_MUCH);
                }else{

                    System.out.println(YES+"!");
                    guessed = true;
                    attemps = i;
                    i = L;

                }


        }
        if(guessed){
            System.out.println(CONGRATULATIONS+", "+attemps+" - tyle prób zajęło Ci odgadnięcie liczby "+generatedNumber);
        }else{
            System.out.println(UNFORTUNATELY+", wyczerpałeś limit prób ("+L+") do odgadnięcia liczby "+generatedNumber);
        }



    }
    public static void progressBar(int maxInputs, int currentInputs) {
        System.out.print("Twoje próby: [");
        int i;
        for (i = 0; i < currentInputs; i++) {
            System.out.print("*");
        }
        for (i = 0; i < maxInputs-currentInputs; i++) {
            System.out.print(".");
        }
        System.out.println("]");

    }
    public static int generateNumber(int number){
        Random r = new Random();
        return r.nextInt(number) + 1;

    }
}
