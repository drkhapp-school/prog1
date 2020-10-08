/**
 * Objectif: Un jeu de nombre mystère consiste à trouver un nombre entre 1 et
 * 99, sélectionné au hasard, selon un nombre d’essais limités à cinq.
 *
 * @author Jean-Philippe Miguel-Gagnon
 * Session A2020
 */

package com.drkhapp;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int nbMin; // plus petit nombre
        int nbMax; // plus gros nombre
        int nbMyst; // nombre à trouver
        int nbLimit; // nombre de tentative
        int nbEssaie; // tentative du joueur
        boolean reussi; // si la personne a réussi
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        nbMin = 0;
        nbMax = 100;
        nbLimit = 1;
        nbMyst = rand.nextInt(99) + 1;
        reussi = false;

        System.out.println("--------------------------------");
        System.out.println("Trouver un nombre entre 1 et 99.");
        System.out.println("Vous avez 5 essaies.");
        System.out.println("--------------------------------\n");

        do {
            try {
                System.out.print("(" + nbLimit + "/5) " + nbMin + " < ? < " + nbMax + " : ");
                nbEssaie = scan.nextInt();

                if (nbEssaie < nbMyst)
                    nbMin = nbEssaie;
                else if (nbEssaie > nbMyst)
                    nbMax = nbEssaie;
                else {
                    reussi = true;
                    break;
                }

                nbLimit++;
            } catch (InputMismatchException e) {
                System.out.println("Rentrer un entier.");
                scan.next();
            }
        } while (nbLimit >= 5);

        if (reussi)
            System.out.println("Félicitation!");
        else
            System.out.println("Désolé! Le nombre était: " + nbMyst);

        scan.close();
    }
}
