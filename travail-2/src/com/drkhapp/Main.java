/**
 * Objectif : TP2 qui consiste à créer un jeu de de multiplication qui permet au joueur de choisir une table de 2 à 12 (2 et 12 sont inclus) et detenter de trouver la réponse aux dix expressions générées aléatoirement par le programme.]
 *
 * @author : Jean-Philippe Miguel-Gagnon
 * Automne 2020
 */
package com.drkhapp;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.StringJoiner;

public class Main {

    public static final String RESET = "\u001B[0m"; // text blanc
    public static final String RED = "\u001B[31m"; // text rouge
    public static final String BLUE = "\u001B[34m"; // text bleu

    /**
     * Demande au joueur une nombre entre 2 et 12
     *
     * @return la table choisi
     */
    public static int choisirTable() {
        int i = 0; // table à retourner
        boolean valid = false; // si l'entrée est valid
        Scanner scan = new Scanner(System.in);

        do {
            try {
                System.out.print(RESET + "-> Choisir une table (2 à 12): ");
                i = scan.nextInt();

                if (i >= 2 && i <= 12) {
                    valid = true;
                }
            } catch (InputMismatchException e) {
                scan.next();
            }
        } while (!valid);

        return i;
    }

    /**
     * Crée un nombre aléatoire entre 2 et 12
     *
     * @return le nombre aléatoire
     */
    public static int genererNbAleatoire() {
        Random rand = new Random();
        return rand.nextInt(11) + 2;
    }

    /**
     * Crée un nouveau tableau avec un int d'ajouté
     *
     * @param tab le tableau
     * @param i   le int qu'on ajoute
     * @return le tableau avec l'élément ajoutée
     */
    public static int[] addElemTableau(int[] tab, int i) {
        int[] newTab = new int[tab.length + 1]; // le nouveau tableau à retourner

        for (int e = 0; e <= tab.length; e++) {
            if (e == tab.length) {
                newTab[e] = i;
            } else {
                newTab[e] = tab[e];
            }
        }
        return newTab;
    }

    /**
     * Crée une liste de tous les résultats
     *
     * @param tab le tableau de données
     * @return string contenant tous les éléments formatées
     */
    public static String statsTableau(int[] tab) {
        StringJoiner stats = new StringJoiner(" "); // le string à retourner

        for (int i : tab) {
            String str = i + "/10";
            stats.add(str);
        }

        return stats.toString();
    }

    /**
     * Crée une moyenne avec tous les int d'un tableau
     *
     * @param tab le tableau à évaluer
     * @return double contenant la moyenne
     */
    public static double moyenneTableau(int[] tab) {
        double moy = 0; // la moyenne à retourner

        for (int e : tab) {
            moy += e;
        }

        return (moy / tab.length);
    }

    /**
     * Demande au joueur si il veut continuer
     *
     * @return true si le joueur accepte, sinon false
     */
    public static boolean validerRejouer() {
        boolean status = false; // le status à retourner
        boolean valid = false; // si l'entrée est valid
        char c; // l'entrée du joueur
        Scanner scan = new Scanner(System.in);

        do {
            try {
                System.out.print(RESET + "Voulez-vous rejouer encore (o/n) ? ");
                c = Character.toLowerCase(scan.next().charAt(0));

                if (c == 'o' || c == 'n') {
                    valid = true;
                    status = c == 'o';
                }

            } catch (InputMismatchException e) {
                scan.next();
            }
        } while (!valid);

        return status;
    }

    /**
     * Génère un pourcentage avec un nombre
     *
     * @param d le nombre à évaluer
     * @param i ce que 100% vaut
     * @return string contenant le pourcentage
     */
    public static String enPourcentage(double d, int i) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(d / i * 100) + "%";
    }

    public static void main(String[] args) {
        int table; // table à jouer
        int nbRand; // nombre aléatoire
        int nbEssaie; // la tentative du joueur
        int nbResult; // résultat du joueur apres une partie
        int partieTotal; // nombre de partie au total
        int partieWin; // nombre de partie gagnée
        int partieLoss; // nombre de partie perdue
        int[] tabResult; // tableau des résultats
        boolean veutRejouer; // si le joueur est en jeu
        boolean estValid; // si l'entrée est valid
        Scanner scan = new Scanner(System.in);

        partieTotal = 0;
        partieWin = 0;
        partieLoss = 0;
        tabResult = new int[]{};

        do {
            table = choisirTable();
            nbResult = 10;
            for (int i = 1; i <= 10; i++) {
                nbRand = genererNbAleatoire();
                estValid = false;

                do {
                    try {
                        System.out.print(RESET + "(Essais: " + i + "/10) " + table + " * " + nbRand + " = ");
                        nbEssaie = scan.nextInt();
                        estValid = true;

                        if (table * nbRand != nbEssaie) {
                            System.out.println(RED + table + " * " + nbRand + " = " + table * nbRand);
                            nbResult--;
                        }

                    } catch (InputMismatchException e) {
                        scan.next();
                    }
                } while (!estValid);
            }

            partieTotal++;
            tabResult = addElemTableau(tabResult, nbResult);

            if (nbResult < 9) {
                partieLoss++;
                System.out.println(BLUE + "\n-> Vous avez perdu! " + nbResult + " / 10");
            } else {
                partieWin++;
                System.out.println(BLUE + "\n-> Vous avez gagnée! " + nbResult + " / 10");
            }

            System.out.println(BLUE + "\nNombre de parties jouées: " + partieTotal);
            System.out.println(BLUE + "Nombre de parties gagnées: " + partieWin);
            System.out.println(BLUE + "Nombre de parties perdues: " + partieLoss);
            System.out.println(BLUE + "Résultat de toutes les parties jouées: " + statsTableau(tabResult));
            System.out.println(BLUE + "Moyenne des résultats: " + enPourcentage(moyenneTableau(tabResult), 10) + "\n");

            veutRejouer = validerRejouer();
        } while (veutRejouer);
    }
}
