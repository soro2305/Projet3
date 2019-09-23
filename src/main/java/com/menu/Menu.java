package com.menu;

import org.apache.log4j.Logger;

import java.util.Scanner;

public class Menu {
    private static Logger logger = Logger.getLogger(Menu.class);


    public void startMenuUser() {
        //Initialisation des instances
        boolean isValid = false;
        Mode choixObj = new Mode();
        Scanner input = new Scanner(System.in);
        char option = '\0';
         logger.info("Chargement du menu principal");
        //===== Menu d accueil ======
        while (isValid == false) {
            System.out.println("\n");
            System.out.println("Menu principal");
            System.out.println("Entre une lettre correspondant à l'option désirée");
            System.out.println("A-Challenger");
            System.out.println("B-Déffenseur");
            System.out.println("C-Duel");
            System.out.println("D-Quitter");

            logger.debug("Récupération de l'entrée clavier ");
            option = input.next().charAt(0); // ===== Initialisation de la var char options afin de récupérer le 1er caractère saisie =======
            System.out.println("\n");
            // Utilisation de switch contient les choix du menu
            logger.debug("Paramètres options : " + option);
            switch (option) {
                case 'A'://===== Choix A envoie méthode challenger ======
                    logger.info("Choix du mode Challenger");
                    System.out.println("============================= Bienvenue dans le mode Challenger ========================== \n");
                    choixObj.challenger();
                    isValid = true;
                    break;
                case 'B'://====== Choix B envoie méthode defensseur =======
                    logger.info("Choix du mode Déffenseur");
                    System.out.println("============================ Bienvenue dans le mode Déffenseur =========================== \n");
                    choixObj.deffenseur();
                    isValid = true;
                    break;
                case 'C'://====== Choix C envoie méthode duel =======
                    System.out.println("=========================== Bienvenue dans le mode Duel ================================== \n");
                    System.out.println("Le premier à trouver la combinaison adverse gagne la partie !!! \n");//Pas de manches dans ce mode
                    choixObj.duel();
                    isValid = true;
                    break;
                case 'D'://====== Choix E termine le programme =======
                    System.out.println("Vous avez choisi de Quitter");
                    isValid = true;
                    break;
                //Message en cas d'entrée invalide
                default:
                    System.out.println("Entrer invalide ! Essayer encore");
                     isValid = false;
            }

        }
    }

    //Menu affiché en fin de partie
    public int endMenuUser() {
        Scanner input = new Scanner(System.in);
        int rejouer = 0;
        System.out.println("\n");
        System.out.println("Menu Express !!!");
        System.out.println("Entre une lettre correspondant à l'option désirée ");
        System.out.println("A-Rejouer même mode");
        System.out.println("B-Retour menu principale");
        System.out.println("C-Quitter l'application");
        char option2 = '\1';
        option2 = input.next().charAt(0); // Initialisation des options afin de récupérer le 1er caractère saisie
        switch (option2) {

            case 'A':
                rejouer = 1;//Que rejouer soit égale à 1 est la condition pour relancer le même mode

                break;
            case 'B':
                System.out.println("\n \n \n ");
                startMenuUser();
            case 'C':
                System.out.println("\n \n \n ");
                break;
            default:
                System.out.println("Entrer invalide !");
        }
        System.out.println("\n \n \n ");
        return rejouer;
    }


}
