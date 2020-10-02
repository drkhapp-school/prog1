/**
 * Objectif: Un jeu de nombre mystère consiste à trouver un nombre entre 1 et 99, sélectionné au hasard, selon un nombre d’essais limités à cinq.
 *
 * @author Jean-Philippe Miguel-Gagnon
 * Session A2020
 */

package com.drkhapp;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int min; // plus petit nombre
        int max; // plus gros nombre
        int myst; // nombre mystère
        int limit; // limit
        int essaie; // la tentative
        boolean reussi; // si la personne a reussi
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        min = 0;
        max = 100;
        myst = rand.nextInt(101) - 1;
        limit = 0;
        reussi = false;

        do {
            limit++;
            System.out.print("(" + limit + "/5) " + min + " < ? < " + max + " : ");
            essaie = scan.nextInt();

            if (essaie == myst) {
                reussi = true;
                break;
            } else if (essaie > myst && essaie < max) {
                max = essaie;
            } else if (essaie < myst && essaie > min)
                min = essaie;

        } while (limit < 5);

        if (reussi)
            System.out.println("Féliciations! Le nombre était: " + myst);
        else
            System.out.println("Le nombre mystère était: " + myst);

        scan.close();
    }

}
