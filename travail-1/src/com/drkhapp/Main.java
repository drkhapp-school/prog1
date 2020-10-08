package com.drkhapp;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * Objectif: Un jeu de nombre mystère consiste à trouver un nombre entre 1 et
 * 99, sélectionné au hasard, selon un nombre d’essais limités à cinq.
 *
 * @author Jean-Philippe Miguel-Gagnon Session A2020
 */
public class Main {

    public static void main(String[] args) {
        int min; // plus petit nombre
        int max; // plus gros nombre
        int myst; // nombre à trouver
        int limit; // nombre de tentative
        int essaie; // tentative du joueur
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        min = 0;
        max = 100;
        myst = rand.nextInt(99) + 1;
        limit = 1;
        essaie = 0;

        System.out.println("--------------------------------");
        System.out.println("Trouver un nombre entre 1 et 99.");
        System.out.println("Vous avez 5 essaies.");
        System.out.println("--------------------------------\n");

        do {
            try {
                System.out.print("(" + limit + "/5) " + min + " < ? < " + max + " : ");
                essaie = scan.nextInt();

                if (essaie < myst)
                    min = essaie;
                else if (essaie > myst)
                    max = essaie;

                limit++;
            } catch (InputMismatchException e) {
                System.out.println("Rentrer un entier.");
                scan.next();
            }
        } while (limit <= 5 && essaie != myst);

        System.out.println("\n--------------------------------");

        if (essaie == myst)
            System.out.println("Félicitation! Le nombre était: " + myst);
        else
            System.out.println("Désolé! Le nombre était: " + myst);

        scan.close();
    }
}
