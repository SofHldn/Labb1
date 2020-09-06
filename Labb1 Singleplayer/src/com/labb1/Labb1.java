package com.labb1;
/*
1. Deklarera variabler
2. Väkommen text till spelaren
3. Loop som får spelet att köras tills spelaren väljer att avbryta
4. Fråga om antal kast
5. Kör metoden playGame med argumenten antalSpelare och antalKast
PlayGame
6. Skapar array som är antalSpelare stor
7. Deklarera två variabler som sparar summorna
8. Kör forloop lika många gånger som antal kast
9. Kör metoden rollDice i loopen som läggs in i arrayen. För varje loop läggs poängen ihop till totalsumma
10.Kasten skrivs ut i loopen och totalsumman skrivs ut när looparna är färdig
11.Metoden result och metoden highScore körs
12.Metoden result jämför summorna och skriver ut om spelaren vann, förlorade eller om det blev lika och
returnerar sedan summan för den som vann
13.Metoden highScore lägger till vinnarens summa till listan, sen kopieras den till ny array (counter lång)
som sorteras och skrivs ut. Om listan (arrayen) är full så dubbleras listan och kopierar över de gamla värdena
till den nya
 */

import java.util.Arrays;
import java.util.Scanner;

public class Labb1 {

    static Scanner sc = new Scanner(System.in);
    static int[] highScoreList = new int[3];
    static int arrayLength = highScoreList.length;
    static int counter = 0;
    static boolean playing = true;
    static boolean error = true;
    static int antalSidor = 6;
    static int antalSpelare = 2;
    static int antalKast = 0;


    public static void main(String[] args) {

        System.out.println("\nVälkommen till Tärningsspelet!");

        while (playing) {

            System.out.print("Hur många kast vill du köra: ");
            antalKast = sc.nextInt();
            sc.nextLine();

            playGame(antalKast, antalSpelare);

            playAgain();
        }

        System.out.println("Hej då!");
    }

    static boolean playAgain() {

        System.out.println("\nVill du spela igen? (ja/nej)");
        String choice = sc.nextLine();

        do {
            if (choice.equalsIgnoreCase("nej")) {
                playing = false;
                error = false;
            } else if (choice.equalsIgnoreCase("ja")) {
                playing = true;
                error = false;
            } else {
                System.out.println("Fel inmatning, vill du spela igen? (ja/nej)");
                choice = sc.nextLine();
                error = true;
            }
        } while (error);

        return playing;
    }

    static void playGame(int antalKast, int antalSpelare) {

        int[] kastArray = new int[antalSpelare];
        int sumPlayer = 0;
        int sumComputer = 0;

        for (int i = 1; i <= antalKast; i++) {
            kastArray[0] = rollDice();
            kastArray[1] = rollDice();

            sumPlayer = sumPlayer + kastArray[0];
            sumComputer = sumComputer + kastArray[1];

            System.out.println("\nKast nummer " + i + ":");
            System.out.println("Spelare: " + kastArray[0] + " poäng");
            System.out.println("Datorn: " + kastArray[1] + " poäng");
        }
        System.out.println("======================");
        System.out.println("Summa spelare: " + sumPlayer);
        System.out.println("Summa datorn: " + sumComputer);

        highScore(result(sumPlayer, sumComputer));
    }

    static int rollDice() {
        return (int) (1 + antalSidor * Math.random());
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

        if (counter == highScoreList.length) {

            int[] arrayDouble = new int[highScoreList.length * 2];

            for (int i = 0; i < counter; i++) {
                arrayDouble[i] = highScoreList[i];
            }

            highScoreList = arrayDouble;
        }

    }

    static int result(int sumPlayer, int sumComputer) {

        if (sumPlayer == sumComputer) {
            System.out.println("\nDet blev lika!");
            return sumPlayer;
        } else if (sumPlayer > sumComputer) {
            System.out.println("\nGrattis du vann!");
            return sumPlayer;
        } else {
            System.out.println("\nDu förlorade!");
            return sumComputer;
        }
    }
}