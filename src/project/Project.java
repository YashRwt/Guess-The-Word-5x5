/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.lang.Integer;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Yash Rawat
 */
public class Project { 

    public static boolean displayCodeWord = false;

    public static String Word; //the Codeword

    public static String[][] Data = new String[5][5]; //the Table

    public static List<Integer> rvldPosns = new ArrayList<Integer>(); //record of revealed Postions 

    public static int table = 0;       //Declaring Variables For the Frountend_Table
    public static String a1, a2, a3, a4, a5, b1, b2, b3, b4, b5, c1, c2, c3, c4, c5, d1, d2, d3, d4, d5, e1, e2, e3, e4, e5 = "?";
    public static List<Integer> wordcrdnts = new ArrayList<Integer>(); //Postion of Hidden Word

    public static void main(String[] args) throws FileNotFoundException {
        menu();
    }

    private static void menu() throws FileNotFoundException {   //Mainmenu

        System.out.println("");
        System.out.println("**  Welcome to the Main Menu  **");
        System.out.println("");
        System.out.println("1.NewGame");
        System.out.println("2.ExitGame");
        System.out.print("Pls Enter Your Choice : ");
        Scanner Scan = new Scanner(System.in);
        int choice = Scan.nextInt();

        if (choice == 1) {              //NewGame
            System.out.println("");

            Project W = new Project();  //Setting Up Game ->

            //Calling the functions
            Word = W.getRandomWord();

            if (displayCodeWord == true) { //Cheat 
                System.out.println(Word);
            }
            insertRandomElements(Data);
            Data = insertCodeWord(Data, Word);

            //Resets the Front End Table
            a1 = a2 = a3 = a4 = a5 = b1 = b2 = b3 = b4 = b5 = c1 = c2 = c3 = c4 = c5 = d1 = d2 = d3 = d4 = d5 = e1 = e2 = e3 = e4 = e5 = "?";
            table = showFEtable();
            gameMenu();
        }

        if (choice == 2) {              //Exit the Game(close the program)

            System.out.println("");
            System.out.println("*** Thanks For Playing ***");
            System.out.println("");
            System.exit(0);

        } else {
            //Resets the Front End Table
            a1 = a2 = a3 = a4 = a5 = b1 = b2 = b3 = b4 = b5 = c1 = c2 = c3 = c4 = c5 = d1 = d2 = d3 = d4 = d5 = e1 = e2 = e3 = e4 = e5 = "?";
            
            menu();
        }

    }

    private static void gameMenu() throws FileNotFoundException {       //Gamemenu

        int checkno = revealedlettercheck(); //Checking The Revealed Postions\
        
        if(checkno>4){      //When The CodeWord has already been revealed  
            System.out.print("");
            System.out.print("Sry! You have Aleardy exposed the letter");
            System.out.print("            Pls Try Again");
            
                //Directing back to Main Menu
            wordcrdnts.clear();//Resets the count of revealed postions and Word
            rvldPosns.clear();
            menu();
            
        }else{              //when it Hasn't Been Reveled

        System.out.println("");
        System.out.println("1.Reveal");
        System.out.println("2.Guess Word");
        System.out.println("3.Exit Game");
        System.out.print("Pls Enter Your Choice : ");

        Scanner Scann = new Scanner(System.in);
        int input = Scann.nextInt();

        if (input == 1) {           //reveal a letter

            System.out.println("");
            System.out.println("(e.g.,\"14\" for row 1 column 4) ->14");
            System.out.print("Enter position as a two-digit index : ");
            Scanner Scanned = new Scanner(System.in);
            int postn = Scanned.nextInt();

            rvldPosns.add(postn);   //keeping counts of tries

            List<Integer> updtPosn = new ArrayList<Integer>();
            updtPosn.add(postn);
            letterReveal(updtPosn); //callin function with (Postion)
            gameMenu();
        }
        if (input == 2) {                       // Guess_Option

            System.out.println("");
            System.out.print("Enter the HiddenWord : ");
            Scanner Scanny = new Scanner(System.in);
            String guess = Scanny.nextLine();

            if (guess.equals(Word)) {       //Right Guess

                //revealing the letter
                letterReveal(wordcrdnts);
                int Score = 25 - rvldPosns.size();

                System.out.println("");     //Celebrations Msg and Score
                System.out.println("  ***  Whoo! Right Answer!! :) ***");
                System.out.println("Conguratulations! Your Score is :" + Score);
                System.out.println("");

                System.exit(0);

            } else {                        //Wrong Guess
                System.out.println("");
                System.out.println("  Wrong Guess :(");

                showFEtable();
                gameMenu();
            }
        }

        if (input == 3) {           //Exit option

            //Resets the count of revealed postions and Word
            wordcrdnts.clear();
            rvldPosns.clear();
            menu();
        } else {
            table = showFEtable();
            gameMenu();
        }
        }
        
    }

    private static void letterReveal(List<Integer> posnList) throws FileNotFoundException {

        for (int i = 0; i < posnList.size(); i++) {
            int postn = posnList.get(i);

            if (postn == 11) {
                a1 = Data[0][0].toUpperCase();            }
            if (postn == 12) {
                a2 = Data[0][1].toUpperCase();            }
            if (postn == 13) {
                a3 = Data[0][2].toUpperCase();            }
            if (postn == 14) {
                a4 = Data[0][3].toUpperCase();            }
            if (postn == 15) {
                a5 = Data[0][4].toUpperCase();            }

            if (postn == 21) {
                b1 = Data[1][0].toUpperCase();            }
            if (postn == 22) {
                b2 = Data[1][1].toUpperCase();            }
            if (postn == 23) {
                b3 = Data[1][2].toUpperCase();            }
            if (postn == 24) {
                b4 = Data[1][3].toUpperCase();            }
            if (postn == 25) {
                b5 = Data[1][4].toUpperCase();            }

            if (postn == 31) {
                c1 = Data[2][0].toUpperCase();            }
            if (postn == 32) {
                c2 = Data[2][1].toUpperCase();            }
            if (postn == 33) {
                c3 = Data[2][2].toUpperCase();            }
            if (postn == 34) {
                c4 = Data[2][3].toUpperCase();            }
            if (postn == 35) {
                c5 = Data[2][4].toUpperCase();            }

            if (postn == 41) {
                d1 = Data[3][0].toUpperCase();            }
            if (postn == 42) {
                d2 = Data[3][1].toUpperCase();            }
            if (postn == 43) {
                d3 = Data[3][2].toUpperCase();            }
            if (postn == 44) {
                d4 = Data[3][3].toUpperCase();            }
            if (postn == 45) {
                d5 = Data[3][4].toUpperCase();            }

            if (postn == 51) {
                e1 = Data[4][0].toUpperCase();            }
            if (postn == 52) {
                e2 = Data[4][1].toUpperCase();            }
            if (postn == 53) {
                e3 = Data[4][2].toUpperCase();            }
            if (postn == 54) {
                e4 = Data[4][3].toUpperCase();            }
            if (postn == 55) {
                e5 = Data[4][4].toUpperCase();            }
        }
        //Calling to Reveal the letter
        table = showFEtable();

        return;
    }

    private static String[][] insertRandomElements(String[][] table) {

        String alphabets = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();

        for (int i = 0; i < table.length; i++) { //for loop to Fill up the Table

            for (int j = 0; j < table[i].length; j++) { //With Random Alphabets

                char Alphabet = alphabets.charAt(rand.nextInt(alphabets.length()));
                String letter = String.valueOf(Alphabet);

                table[i][j] = letter;   //Random letter Assigned as a "String"

            }

        }
        return table;   //Returing the Updated Table

    }

    private static String[][] insertCodeWord(String[][] Data, String Word) {

        int[][][] desgLoc = { //An Array of Cordinates or Desired Location
            {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}},
            {{1, 0}, {1, 1}, {1, 2}, {1, 3}, {1, 4}}, //To Place
            {{2, 0}, {2, 1}, {2, 2}, {2, 3}, {2, 4}},
            {{3, 0}, {3, 1}, {3, 2}, {3, 3}, {3, 4}}, //The CodeWord
            {{4, 0}, {4, 1}, {4, 2}, {4, 3}, {4, 4}},
            {{0, 0}, {1, 0}, {2, 0}, {3, 0}, {4, 0}}, //In a "Desired"
            {{0, 1}, {1, 1}, {2, 1}, {3, 1}, {4, 1}},
            {{0, 2}, {1, 2}, {2, 2}, {3, 2}, {4, 2}}, //Manner
            {{0, 3}, {1, 3}, {2, 3}, {3, 3}, {4, 3}},
            {{0, 4}, {1, 4}, {2, 4}, {3, 4}, {4, 4}},};

        Random rand = new Random();
        int CombNum = rand.nextInt(desgLoc.length); //Takin A Random one from desgLoc

        for (int i = 0; i < desgLoc.length; i++) {  //For Loop for Embedding the Word

            if (i == CombNum) {

                for (int j = 0; j < desgLoc[i].length; j++) {

                    int f = 0;
                    int b = 0;
                    for (int k = 0; k < 2; k++) {

                        if (k == 0) {
                            f = desgLoc[i][j][k];
                        } else {
                            b = desgLoc[i][j][k];
                        }
                    }
                    //Keeping Record of where the Word is hidden 
                    wordcrdnts.add((10 * (f + 1)) + b + 1);

                    char wordElements = Word.charAt(j);     //Embedding the Word
                    Data[f][b] = String.valueOf(wordElements);
                }
            }
        }

        return Data;    //Returing the Table with the Word

    }

    private static int showFEtable() {          //Front End Table
        System.out.println("");
        System.out.println("r\\c| 1 2 3 4 5");
        System.out.println("---+----------");
        System.out.println(" 1 | " + a1 + " " + a2 + " " + a3 + " " + a4 + " " + a5);
        System.out.println(" 2 | " + b1 + " " + b2 + " " + b3 + " " + b4 + " " + b5);
        System.out.println(" 3 | " + c1 + " " + c2 + " " + c3 + " " + c4 + " " + c5);
        System.out.println(" 4 | " + d1 + " " + d2 + " " + d3 + " " + d4 + " " + d5);
        System.out.println(" 5 | " + e1 + " " + e2 + " " + e3 + " " + e4 + " " + e5);
        System.out.println("");
        //Resets the Front End Table
        a1 = a2 = a3 = a4 = a5 = b1 = b2 = b3 = b4 = b5 = c1 = c2 = c3 = c4 = c5 = d1 = d2 = d3 = d4 = d5 = e1 = e2 = e3 = e4 = e5 = "?";
        return 0;
    }

    private static int revealedlettercheck() { //checking the Revealed Letter
        int count = 0;
        for (int i = 0; i < wordcrdnts.size(); i++) {
            for (int j = 0; j < rvldPosns.size(); j++) {

                if ( wordcrdnts.get(i).equals(rvldPosns.get(j)) ){
                    count = count + 1;  //Adding 1 for every hit(revaled & Codeword )
                }
            }
        }
        return count;
    }

    private String getRandomWord() throws FileNotFoundException {

        String fileLocation = System.getProperty("user.dir");           //File Path
        String filePath = fileLocation + File.separator + "wordlist.txt";

        File file = new File(filePath);         //Calling & Scannin the file
        Scanner reader = new Scanner(file);

        List<String> list = new ArrayList<>();

        while (reader.hasNextLine()) {          //Adding each word  to the list
            list.add(reader.nextLine());
        }

        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));     //Sending a Rondom Letter Back

    }

}
2