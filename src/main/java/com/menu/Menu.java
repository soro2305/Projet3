package com.menu;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

/***
 * <b>Contient les menus de début et de fin de partie.</b>
 *<p> Le menu de début de partie qui permet de:</p>
 * <ul>
 *     <li>Choisir un mode de jeu</li>
 *     <li>Quitter l'application</li>
 * </ul>
 *<p> Le menu de fin de partie qui permet de:</p>
 * <ul>
 *     <li>Choisir de rejouer au même mode de jeu</li>
 *     <li>Retourner au menu principal</li>
 *     <li>Quitter l'application</li>
 * </ul>
 * @author Amet Soro
 * @version 1.0
 */
public class Menu {

    /**
     * Valeur qui représente le choix de rejouer au même mode de jeu.
     */
    private final static int CHOIXREJOUER = 1;
    /**
     * Valeur qui représente le choix de retourner au menu principal.
     */
    private final static int CHOIXMENUPRINCIPAL = 2;
    /**
     * Valeur qui représente le choix de quitter l'application.
     */
    private final static int CHOIXQUITTER = 3;

    /**
     * Instanciation d'objet du type Logger servant a généré les logs du Menu.
     */
    private static Logger logger = Logger.getLogger(Menu.class);
    /**
     * Instanciation d'objet du type Scanner servant a saisir le choix du menu.
     */
    private Scanner input = new Scanner(System.in);

    /**
     * Menu qui permet de sélectionner le mode de jeu.
     *
     * @return true pour quitter l'application.
     */
    public boolean startMenuUser() {

        logger.info("Arriver dans le menu startMenu");
        //Initialisation des instances
        boolean isValid = false;
        int lastChoice = 0;
        Mode choixObj = new Mode();
        if (!choixObj.isParamValid()) {
            System.out.println("Le fichier properties est introuvable ");
            return false;
        }
        char option = '\0';
        //===== Menu d accueil ======
        while (isValid == false) {
            System.out.println("\n");
            System.out.println("Menu principal");
            System.out.println("Entré une lettre correspondant à l'option désirée");
            System.out.println("A-Challenger");
            System.out.println("B-Défenseur");
            System.out.println("C-Duel");
            System.out.println("D-Quitter");
            logger.info("Chargement du menu principal");

            logger.debug("Récupération de l'entrée clavier ");
            option = input.next().charAt(0); // ===== Initialisation de la var char options afin de récupérer le 1er caractère saisie =======
            System.out.println("\n");
            // Utilisation de switch contient les choix du menu
            logger.debug("Choix options entree par l'utilisateur : " + option);
            switch (option) {
                case 'A'://===== Choix A envoie méthode challenger ======
                    do {
                        logger.info("Choix du mode Challenger");
                        System.out.println("============================= Bienvenue dans le mode Challenger ========================== \n");
                        choixObj.challenger();
                        lastChoice = endMenuUser();
                    } while (lastChoice == CHOIXREJOUER);
                    break;
                case 'B'://====== Choix B envoie méthode defensseur =======
                    do {
                        logger.info("Choix du mode Défenseur");
                        System.out.println("============================ Bienvenue dans le mode Déffenseur =========================== \n");
                        choixObj.defenseur();
                        lastChoice = endMenuUser();
                    } while (lastChoice == CHOIXREJOUER);
                    break;
                case 'C'://====== Choix C envoie méthode duel =======
                    do {
                        logger.info("Choix du mode duel");
                        System.out.println("=========================== Bienvenue dans le mode Duel ================================== \n");
                        System.out.println("Le premier à trouver la combinaison adverse gagne la partie !!! \n");//Pas de manches dans ce mode
                        choixObj.duel();
                        lastChoice = endMenuUser();
                    } while (lastChoice == CHOIXREJOUER);
                    break;
                case 'D'://====== Choix E termine le programme =======
                    logger.info("Choix quitter l'application");
                    System.out.println("Vous avez choisi de Quitter");
                    isValid = true;
                    break;
                //Message en cas d'entrée invalide
                default:
                    logger.info("Choix default activer");
                    logger.error("Entrée invalide vérifier que votre réponse soit bien une des lettres majuscule");
            }
            if (lastChoice == CHOIXQUITTER) {
                isValid = true;
            }

        }
        return true;
    }

    /**
     * Menu qui permet de rejouer retourner au menu principale ou quitter l'application.
     *
     * @return Le choix de l'utilisateur au menu principal :
     * <ul>
     *     <li>Rejouer au même mode de jeu</li>
     *     <li>Retourner au menu principal</li>
     *     <li>Quitter le jeu</li>
     * </ul>
     *
     */
    //Menu affiché en fin de partie
    public int endMenuUser() {
        logger.info("Arriver dans le menu endMenu");
        int choixMenu = 0;
        boolean isValid = false;
        while (!isValid) {
            System.out.println("\n");
            System.out.println("Menu Express !!!");
            System.out.println("Entre une lettre correspondant à l'option désirée ");
            System.out.println("A-Rejouer même mode");
            System.out.println("B-Retour menu principal");
            System.out.println("C-Quitter l'application");
            char option2 = '\1';
            option2 = input.next().charAt(0); // Initialisation des options afin de récupérer le 1er caractère saisie par l'utilisateur
            logger.debug("Choix options entree par l'utilisateur : " + option2);
            switch (option2) {

                case 'A':
                    choixMenu = CHOIXREJOUER;
                    isValid = true;
                    break;
                case 'B':
                    System.out.println("\n \n \n ");
                    choixMenu = CHOIXMENUPRINCIPAL;

                    isValid = true;
                    break;
                case 'C':
                    //Fait quitter application
                    System.out.println("\n \n \n ");
                    isValid = true;
                    choixMenu = CHOIXQUITTER;
                    break;
                default:
                    System.out.println("Entrée invalide vérifier que votre réponse soit bien une des lettres majuscule");
                    isValid = false;//En cas d'entrer invalide perpétue la boucle
            }
            System.out.println(" \n ");

        }
        return choixMenu;

    }
}


