package com.menu;

import com.joueur.Ordi;
import com.joueur.Player;
import com.joueur.User;
import org.apache.log4j.Logger;

public class Mode {
    private static Logger logger = Logger.getLogger(Mode.class);
    Parametres settingsObj = new Parametres();
    Menu menuObj = new Menu();
    Player userObj = new User();
    Player ordiObj = new Ordi();
    String codeUser = "";
    String codeOrdi = "1";
    String codeTempo = "2";
    boolean rejouer = false;
    int nombreUnit = settingsObj.getNombreUnit();
    String maxString = settingsObj.getMax();
    String minString = settingsObj.getMin();
    int maxInt = Integer.parseInt(maxString);
    int minInt = Integer.parseInt(minString);
    //   String[][] retour =new String[nombreUnit][2];

    public int tours = settingsObj.getToursTotale();

    //Méthode du mode Challenger
    public void challenger() {
        logger.info("Arriver dans le mode Challenger");
        //Initialisations des instances
        //conteurs for loop
        int i;
        int j;
        //Création combinaison EA
        codeOrdi = ordiObj.generCodeString(minInt, maxInt);
        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous " + (tours) + " manches");
        System.out.println("            Il y aura " + nombreUnit + " chiffres par combinaison");
        if (settingsObj.isDevMode()) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //Boucle paramétre le nombre de tentative via la var "tours"
        for (i = 1; i <= tours; i++) {
            codeUser = userObj.attemptUser();
            if (codeUser.equals(codeOrdi)) {
                System.out.println("\n");
                System.out.println("Bravo c'est Gagné la solution était " + codeOrdi + " !!!\n\n");//Conditions pour quitter la boucle si victoire
                break;
            } else {

                //Méthode pour comparer les deux combinaisons
                System.out.print("Proposition : " + codeUser + " -> Réponse : ");
                ordiObj.compare(codeUser, codeOrdi);

                System.out.println("\n");
                if (i == tours) {
                    System.out.println("\n ============== GAME OVER ============== ");
                    System.out.println("Vous avez épuisé toutes vos chances. La bonne réponse était " + codeOrdi + "\n");
                }

            }
        }
        //Envoie au menu final
        rejouer = menuObj.endMenuUser();
        if (rejouer == true) challenger(); //Condition pour relancer même mode
    }

    //Méthode du mode Deffenseur
    public void deffenseur() {
        logger.info("Arriver dans le mode Déffenseur");
        //  int tours = settingsObj.tours;//Nombres de tours de la partie
        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous " + (tours) + " manches");
        System.out.println("            Il y aura " + nombreUnit + " chiffres par combinaison");
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser();
        //Définition 1 er combinaison Ordi
        codeOrdi = ordiObj.generCodeString(minInt, maxInt);
        //Boucle paramètre le nombres de tentatives via la var int tours
        for (int i = 1; i <= tours; i++) {
            //Vérifie si les 2 combinaisons sont égales.Stop la partie si true
            if (codeOrdi.equals(codeUser)) {
                System.out.println("\n");
                System.out.println("GAME OVER l'Ordi a trouvé la solution  " + codeUser + " !!!\n\n ");//Conditions pour quitter la boucle si victoire de l'EA
                //Réinitialise ses valeurs en cas de nouvelle partie
                maxString = settingsObj.getMax();
                minString = settingsObj.getMin();
                break;
            } else {

                //Formate l affichage de sortie console
                if (settingsObj.isDevMode())
                    logger.info("Les bornes min, max du generateur sont --> min1 : " + minString + " max1  : " + maxString);

                System.out.print("Proposition : " + codeOrdi + " -> Réponse : ");
                //Méthode pour comparer les deux combinaisons et affiché "+-="
                ordiObj.compare(codeOrdi, codeUser);
                System.out.println("\n");

                //Méthode pour générer nouvelle combinaison Ordi


                // codeTempo = ordiObj.newCodeOrdi(codeOrdi, codeUser);
                String[] retour = ordiObj.newCodeOrdi(codeUser, codeOrdi, minString, maxString);
                codeOrdi = retour[0];
                minString = retour[2];
                maxString = retour[1];


                System.out.println("\n");
                //Affiche Game over quand nombres de tours épuisés
                if (i == tours) {
                    System.out.println("\n\n\n ============== L'ordi a épuisé toutes ses manches c'est GAGNÉ !!! ============== \n");
                }

            }
        }
        //Réinitialise ses valeurs en cas de nouvelle partie
        maxString = settingsObj.getMax();
        minString = settingsObj.getMin();
        maxInt = Integer.parseInt(maxString);
        minInt = Integer.parseInt(minString);
        //Envoie au menu final
        rejouer = menuObj.endMenuUser();
        if (rejouer == true) deffenseur(); //Condition pour relancer mÃªme mode

    }

    //Méthode duel chacun son tour définit une combi, le premier à trouver gagne
    public void duel() {
        logger.info("Arriver dans le mode Duel");
        String tentativeUser = "2";
        String tentativeOrdi = "3";
        boolean sortie = false;
        boolean rejouer = false;

        //Définition combinaison Ordi
        codeOrdi = ordiObj.generCodeString(minInt, maxInt);
        //Définition tentative Ordi
        tentativeOrdi = ordiObj.generCodeString(minInt, maxInt);
        System.out.println(" min int : " + maxInt + " max int : " + maxInt);
        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("            Il y aura " + nombreUnit + " chiffres par combinaison");
        if (settingsObj.isDevMode()) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("========================================================================================================== ");
        System.out.println("==========================================================================================================");

        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser();
        System.out.println("Votre combinaison est : " + codeUser + "\n");
        System.out.println("==========================================================================================================");

        //Do while loop pour continuer tant qu il n'y a pas de vainqueur
        do {
            System.out.println("==========================================================================================================");

            tentativeUser = userObj.attemptUser();
            if (tentativeUser.equals(codeOrdi)) {//compare si les réponses sont égales
                System.out.println("\n");
                System.out.println("Bravo la réponse était : " + codeOrdi + " c'est GAGNÉ !!!");
                maxString = settingsObj.getMax();
                minString = settingsObj.getMin();
                maxInt = Integer.parseInt(maxString);
                minInt = Integer.parseInt(minString);
                sortie = true;// Permets de sortir de la boucle en cas de Victoire
            } else {
                System.out.print("Votre proposition est  " + tentativeUser + " -> Indication : ");
                ordiObj.compare(tentativeUser, codeOrdi);
                System.out.println();
                if (tentativeOrdi.equals(codeUser)) {//compare si les réponses sont égales
                    System.out.println("\n L'Ordi a trouvé : " + codeUser + " GAME OVER !!!");
                    //Réinitialise ses valeurs en cas de nouvelle partie
                    maxString = settingsObj.getMax();
                    minString = settingsObj.getMin();
                    maxInt = Integer.parseInt(maxString);
                    minInt = Integer.parseInt(minString);
                    sortie = true;// Permets de sortir de la boucle en cas de défaite
                    break;
                }
                if (settingsObj.isDevMode()) {
                    logger.info("Les bornes min, max du generateur sont --> minimum : " + minString + " maximum  : " + maxString);
                    logger.info("             Mode Dev activé L'EA a choisie : " + codeOrdi);
                }
                System.out.print("La proposition de l'Ordi est  : " + tentativeOrdi + " -> Indication : ");
                ordiObj.compare(tentativeOrdi, codeUser);
                System.out.println();
                //Définit nouvelles combis et nouveaux rangent Min max baser sur les indications
                String[] retourDuel = ordiObj.newCodeOrdi(codeUser, tentativeOrdi, minString, maxString);
                //Stockage des réponses
                tentativeOrdi = retourDuel[0];
                minString = retourDuel[2];
                maxString = retourDuel[1];
                System.out.println("==========================================================================================================");

            }

        } while (!sortie);
        //Réinitialise ses valeurs par défaults en cas de nouvelle partie
        maxString = settingsObj.getMax();
        minString = settingsObj.getMin();
        maxInt = Integer.parseInt(maxString);
        minInt = Integer.parseInt(minString);
        //Permet de rejouer même mode
        rejouer = menuObj.endMenuUser();
        if (rejouer == true) duel(); //Condition pour relancer même mode
    }

}

