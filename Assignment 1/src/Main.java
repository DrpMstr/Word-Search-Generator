//Made by possibly the worst programmer ever (because I really don't like doing this stuff alone):
//Nathan Murdoch
//The program is supposed to be able to allow you to generate word searches with certain words in them
//
import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();//creates a random input
        printIntro();

        String command;
        do {
            System.out.println("Select an option below to get started.");
            System.out.println("DISCLAIMER: If you just created one, you need to restart to be able to see it.");
            System.out.println("'g' to generate a new word search");
            System.out.println("'p' to print a previously created word search");
            System.out.println("'s' to show the solution of a word search");
            System.out.println("'n' to quit the program");
            command = input.next().toLowerCase();
            switch (command) {
                case "g":
                    generate(input, rand);
                    break;
                case "p":
                    print(input);
                    break;
                case "s":
                    showSolution(input);
                    break;
                case "n":
                    System.out.println("Stopping program...");
                    break;
                default:
                    System.out.println("Invalid input, please try again.");
                    System.out.printf("-----------------------------------------------------------------------%n");
                    break;
            }
        } while (!command.equals("n"));


    }

    public static void printIntro() {//prints the introduction to the puzzle
        System.out.printf("Hello and welcome to WordSearchUltimate!%n This is a word search puzzle-");
        System.out.printf("generator, for you to do word searches!%n");
        System.out.printf("Now, lets get started, shall we?%n");
        System.out.printf("-----------------------------------------------------------------------%n");
    }

    public static void generate(Scanner input, Random rand) throws FileNotFoundException {
        //generates grid, and you can input what goes into the grid, one letter at a time
        int xLength;
        int yLength;
        char xChar = 'X';
        int random = 23;
        int UPRANDLIM = 26;


        System.out.println("Please type finished word search file name, please DON'T put .txt at the end of your file name. (ex. test)");
        String nextInput = input.next();
        PrintStream toFile = new PrintStream(new File(nextInput + ".txt"));//names file being made
        PrintStream toFileAnswer = new PrintStream(new File(nextInput + "Answer.txt"));//names file being made
        System.out.println("How wide do you want your grid to be?");
        xLength = input.nextInt();
        System.out.println("How tall do you want your grid to be?");
        yLength = input.nextInt();
        System.out.println("Now you will be asked what letter you want per grid space in the grid.");
        System.out.println("For example, if you did a 7x7 grid, you would do 49 letters from");
        System.out.println("the entire grid, one at a time going across each row from the top left");
        System.out.println("corner to the bottom right corner.");

        char[] randAlphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
                'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};//26 letters long
                                                                                        //0 to 25

        char[][] grid = new char[xLength][yLength];//double array to make grid boundaries
         for(int row = 0; row < grid.length; row++) {
             System.out.println("onto the next row");
             for (int column = 0; column < grid[row].length; column++)
             {
                 System.out.printf("the %d/%d letter in the %d/%d row%n", column+1, xLength, row+1, yLength);
                 System.out.println("What letter would you want here? (1 for random letter)");
                 String letterChoice = input.next().toUpperCase();
                 char charChoice = letterChoice.charAt(0);//returns first character only
                 if(letterChoice.equals("1")) {
                     random = rand.nextInt(UPRANDLIM - 1);
                     char randLetter = randAlphabet[random];
                     grid[row][column] = xChar;
                     toFileAnswer.print("X ");
                     toFile.printf("%s ", randLetter);

                 } else {
                     grid[row][column] = charChoice;
                     toFileAnswer.printf("%s ", charChoice);
                     toFile.printf("%s ", charChoice);
                 }

             }
             toFile.println(" ");
             toFileAnswer.println(" ");
    }

}




    public static void print(Scanner input) throws FileNotFoundException {
        System.out.println("Choose file to see. DON'T put .txt at the end (ex. test)");
        String fileView = input.next() + ".txt";
        Scanner inputFour = new Scanner(new File(fileView));//selects file user chose that is in the system

        while (inputFour.hasNextLine()) {
            Scanner inputFive = new Scanner(inputFour.nextLine());
                 //sees if next like is there and stops if not
            while (inputFive.hasNext()) {//same thing as above but for the words
                String word = inputFive.next();


                System.out.print(word + " ");
            }//end embed while
            System.out.println();
        }//end while
        System.out.println();
    }//end method

    public static void showSolution(Scanner input) throws FileNotFoundException {//shows solution to word search with all letters not in a word are 'X'
        System.out.println("Choose file to see. DON'T put .txt at the end (ex. test)");
        String fileView = input.next() + "Answer.txt";
        Scanner inputFour = new Scanner(new File(fileView));//selects file user chose that is in the system

        while (inputFour.hasNextLine()) {
            Scanner inputFive = new Scanner(inputFour.nextLine());
            //sees if next like is there and stops if not
            while (inputFive.hasNext()) {//same thing as above but for the words
                String word = inputFive.next();


                System.out.print(word + " ");
            }//end embed while
            System.out.println();
        }//end while
        System.out.println();
    }//end method
}

