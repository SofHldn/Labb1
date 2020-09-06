package com.labb1tarningsspelijava;

import java.util.Arrays;
import java.util.Scanner;


public class Labb1TarningsspelIJava {

    static Scanner sc = new Scanner(System.in);
    static int[] highScoreList = new int[10];
    static int arrayLength = 10;
    static int counter = 0;
    static Boolean playing = true;
    static int antalSpelare = 2;
    static int antalKast = 0;

    public static void main(String[] args) {

        System.out.println("Tryck D för att spela mot datorn, tryck M för att spela multiplayer, 0 för att avsluta");
        String choice = sc.nextLine();

        while (playing) {

            if (choice.equalsIgnoreCase("D")) {
                computerGame();
            } else if (choice.equalsIgnoreCase("M")) {
                multiPlayerGame();
            } else if (choice.equals("0")) {
                playing = false;
            } else {
                System.out.println("Finns inget sådant val");
                System.out.println("Tryck D för att spela mot datorn, tryck M för att spela multiplayer, 0 för att avsluta");
                choice = sc.nextLine();
            }
        }
    }

    static void computerGame() {

        System.out.print("Hur många kast vill du köra (0 för att avsluta): ");
        antalKast = sc.nextInt();
        sc.nextLine();
        antalSpelare = 2;

        if (antalKast == 0) {
            playing = false;
        } else {
            int sumArray[] = playGame(antalSpelare, antalKast);
            highScore(printResultSP(sumArray));
        }
    }

    static void multiPlayerGame() {
        System.out.println("Hur många spelare (0 för att avsluta): ");
        antalSpelare = sc.nextInt();
        System.out.println("Hur många kast: ");
        antalKast = sc.nextInt();
        if (antalSpelare == 0) {
            playing = false;
        } else {
            int sumArray[] = playGame(antalSpelare, antalKast);
            int[] winner = checkResults(sumArray);

            printResultMP(winner);
        }
    }

    static int[] playGame(int antalSpelare, int antalKast) {

        int[] resultArray = new int[antalKast];
        int[] sumArray = new int[antalSpelare];
        int antalSidor = 6;


        for (int i = 0; i < antalSpelare; i++) {
            System.out.println("\nSpelare" + (i + 1));

            for (int j = 0; j < antalKast; j++) {
                resultArray[j] = rollDice(antalSidor);
                System.out.println("Kast nummer " + (j + 1) + " : " + resultArray[j] + " poäng");
                sumArray[i] = sumArray[i] + resultArray[j];
            }
            System.out.println("Summan för Spelare " + (i + 1) + ": " + sumArray[i]);
        }


        //Skriver ut en array med varje spelares totalsumma
        /*System.out.println();
        for(int i = 0; i<antalSpelare; i++){
            System.out.println(sumArray[i]);
        }*/

        return sumArray;

    }

    static int rollDice(int antalSidor) {

        return (int) (1 + antalSidor * Math.random());
    }

    static void printResultMP(int[] winner) {
        int numberOfWinners = 0;

        for (int i = 0; i < winner.length; i++) {
            if (winner[i] > 0) {
                numberOfWinners = numberOfWinners + 1;
            }
        }

        if (numberOfWinners == 1) {
            for (int i = 0; i < winner.length; i++) {
                if (winner[i] > 0) {
                    System.out.println("\nVinnare är spelare " + winner[i] + "!");
                }
            }

        } else if (numberOfWinners > 1) {
            System.out.print("\nDet blev lika! \nVinnarna är: ");
            for (int i = 0; i < winner.length; i++) {
                if (winner[i] > 0) {
                    System.out.print("spelare" + winner[i] + "  ");

                }
            }
            System.out.println();
        }
    }

    static int[] checkResults(int[] sumArray) {
        int max = 0;
        int[] savePos = new int[antalSpelare];

        for (int i = 1; i < sumArray.length; i++) {
            if (sumArray[i] > sumArray[max]) {
                max = i;
            }
        }

        for (int i = 0; i < savePos.length; i++) {
            if (sumArray[max] == sumArray[i]) {
                savePos[i] = i + 1;
            }
        }

        return savePos;
    }

    static void highScore(int result) {

        highScoreList[counter] = result;
        counter = counter + 1;
        int[] arrayToSort = new int[counter];

        for (int i = 0; i < counter; i++) {
            arrayToSort[i] = highScoreList[i];
        }

        Arrays.sort(arrayToSort);

        System.out.println("\n****High Score****");
        for (int i = (counter - 1); i >= 0; i--) {
            System.out.println("        " + arrayToSort[i]);
        }

        if (counter == arrayLength) {

            arrayLength = arrayLength * 2;
            int[] arrayDouble = new int[arrayLength];

            for (int i = 0; i < counter; i++) {
                arrayDouble[i] = highScoreList[i];
            }

            highScoreList = arrayDouble;
        }

    }

    static int printResultSP(int[] sumArray) {

        if (sumArray[0] == sumArray[1]) {
            System.out.println("\nDet blev lika!");
            return sumArray[0];
        } else if (sumArray[0] > sumArray[1]) {
            System.out.println("\nGrattis du vann!");
            return sumArray[0];
        } else {
            System.out.println("\nDatorn är vinnare!");
            return sumArray[1];
        }
    }

}
