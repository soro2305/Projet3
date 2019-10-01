package com.menu;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class Menu {
    //Initialisation du logger pour menu
    private static Logger logger = Logger.getLogger(Menu.class);

    public boolean startMenuUser() throws IOException {
        logger.info("Arriver dans le menu startMenu");
        //Initialisation des instances
        boolean isValid = false;
        Mode choixObj = new Mode();
        if (!choixObj.isParamValid()){
            System.out.println("Le fichier properties est introuvable ");
            return false;
        }
        Scanner input = new Scanner(System.in);
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
                    logger.info("Choix du mode Challenger");
                    System.out.println("============================= Bienvenue dans le mode Challenger ========================== \n");
                    choixObj.challenger();
                    isValid = true;
                    break;
                case 'B'://====== Choix B envoie méthode defensseur =======
                    logger.info("Choix du mode Défenseur");
                    System.out.println("============================ Bienvenue dans le mode Déffenseur =========================== \n");
                    choixObj.defenseur();
                    isValid = true;
                    break;
                case 'C'://====== Choix C envoie méthode duel =======
                    logger.info("Choix du mode duel");
                    System.out.println("=========================== Bienvenue dans le mode Duel ================================== \n");
                    System.out.println("Le premier à trouver la combinaison adverse gagne la partie !!! \n");//Pas de manches dans ce mode
                    choixObj.duel();
                    isValid = true;
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
                    isValid = false;
            }

        }
    return true;
    }
    //Menu affiché en fin de partie
    public boolean endMenuUser() throws IOException {
        logger.info("Arriver dans le menu endMenu");
        //Initialisation des instances
        Scanner input = new Scanner(System.in);
        boolean isValid = false;//Conditions pour vérifier la saisie de l'utilisateur
        boolean rejouer = false;//Conditions pour relancer une partie
         while (isValid == false) {
             System.out.println("\n");
             System.out.println("Menu Express !!!");
             System.out.println("Entre une lettre correspondant à l'option désirée ");
             System.out.println("A-Rejouer même mode");
             System.out.println("B-Retour menu principale");
             System.out.println("C-Quitter l'application");
             char option2 = '\1';
             option2 = input.next().charAt(0); // Initialisation des options afin de récupérer le 1er caractère saisie par l'utilisateur
             logger.debug("Choix options entree par l'utilisateur : " + option2);
             switch (option2) {

                 case 'A':
                     rejouer = true;//Que rejouer soit égale à 1 est la condition pour relancer le même mode
                     isValid = true;
                     break;
                 case 'B':
                     System.out.println("\n \n \n ");
                     startMenuUser();//Renvoie au menu principal
                     isValid = true;
                 case 'C':
                     //Fait quitter application
                     System.out.println("\n \n \n ");
                     isValid = true;
                     break;
                 default:
                     System.out.println("Entrée invalide vérifier que votre réponse soit bien une des lettres majuscule");
                     isValid = false;//En cas d'entrer invalide perpétue la boucle
             }
             System.out.println(" \n ");

         }
        return rejouer;// Retourne rejouer  égale à true pour relancer le même mode

}
}


