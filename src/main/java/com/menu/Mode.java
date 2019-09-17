package com.menu;

import com.joueur.Ordi;
import com.joueur.Player;
import com.joueur.User;

import java.util.ResourceBundle;

public class Mode  {
    Parametres settingsObj = new Parametres();
    Menu menuObj = new Menu();
    Player userObj = new User();
    Player ordiObj = new Ordi();
    int max = settingsObj.getMax();
    int min = settingsObj.getMin();
    String codeUser = "";
    String codeOrdi = "1";
    String codeTempo = "2";
    int rejouer = 0;
    int nombreUnit = settingsObj.getNombreUnit();
    public int tours = settingsObj.getToursTotale() ;

    //Méthode du mode Challenger
    public void challenger() {
        //Initialisations des instances
        //conteurs for loop
        int i;
        int j;
        //Création combinaison EA
        codeOrdi = ordiObj.generCodeString(min, max);
        //Infos paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous "+(tours)+" manches");
        System.out.println("            Il y aura "+ nombreUnit + " chiffres par combinaison");
        if (settingsObj.devMode==true) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
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
                    System.out.println("Vous avez épuisé toutes vos chances. La bonne réponse était "+codeOrdi +"\n");
                }

            }
        }
        //Envoie au menu final
        rejouer = menuObj.endMenuUser();
        if (rejouer == 1) challenger(); //Condition pour relancer même mode
    }

    //Méthode du mode Deffenseur
    public void déffenseur() {
        //  int tours = settingsObj.tours;//Nombres de tours de la partie
        //Infos paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous "+(tours)+" manches");
        System.out.println("            Il y aura "+ nombreUnit + " chiffres par combinaison");
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser();
        //Définition 1 er combinaison Ordi
        codeOrdi = ordiObj.generCodeString(min, max);
        //Boucle paramètre le nombres de tentatives via la var int tours
        for (int i = 1; i <= tours; i++) {
            //Vérifie si les 2 combinaisons sont égales.Stop la partie si true
            if (codeOrdi.equals(codeUser)) {
                System.out.println("\n");
                System.out.println("GAME OVER l'Ordi a trouvé la solution  " + codeUser + " !!!\n\n ");//Conditions pour quitter la boucle si victoire de l'EA
                break;
            } else {
                //Formate l affichage de sortie console
                System.out.print("Proposition : " + codeOrdi + " -> Réponse : ");
                //Méthode pour comparer les deux combinaisons et affiché "+-="
                ordiObj.compare(codeOrdi, codeUser);
                //Méthode pour générer nouvelle combinaison Ordi

                codeTempo = ordiObj.newCodeOrdi(codeOrdi, codeUser);
                codeOrdi = ordiObj.newCodeOrdi(codeUser, codeTempo);

                //  System.out.println("\n"+codeTempo);
                //   codeOrdi = ordiObj.new1(codeTempo,codeOrdi,codeUser);
                System.out.println("\n");
                //Affiche Game over quand nombres de tours épuisés
                if (i == tours) {
                    System.out.println("\n\n\n ============== L'ordi a épuisé toutes ses manches c'est GAGNÉ !!! ============== \n");
                }

            }
        }
        //Envoie au menu final
        rejouer = menuObj.endMenuUser();
        if (rejouer == 1) déffenseur(); //Condition pour relancer mÃªme mode

    }
    //Méthode duel chacun son tour définit une combi, le premier à trouver gagne
    public void duel() {
        String tentativeUser = "2";
        String tentativeOrdi = "3";
        boolean sortie = false;
        int rejouer = 0;

        //Définition combinaison Ordi
        codeOrdi = ordiObj.generCodeString(min, max);
        //Définition tentative Ordi
        tentativeOrdi = ordiObj.generCodeString(min,max);
        //Infos paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("            Il y aura "+ nombreUnit + " chiffres par combinaison");
        if (settingsObj.devMode==true) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser();
        System.out.println("Votre combinaison est : "+codeUser+"\n");
        System.out.println("le code unit est " + settingsObj.getNombreUnit());
        //Do while loop pour continuer tant qu il n'y a pas de vainqueur
        do {
            tentativeUser = userObj.attemptUser();
            if (tentativeUser.equals(codeOrdi)){//compare si les réponses sont égales
                System.out.println("\n");
                System.out.println("Bravo la réponse était : "+codeOrdi+" c'est GAGNÉ !!!");
                sortie = true;// Permets de sortir de la boucle en cas de Victoire
            }else {
                System.out.print("Votre proposition est  " + tentativeUser + " -> Indication : ");
                ordiObj.compare(tentativeUser, codeOrdi);
                System.out.println();
                if (tentativeOrdi.equals(codeUser)) {//compare si les réponses sont égales
                    System.out.println("L'Ordi a trouvé : " + codeUser + " GAME OVER !!!");
                    sortie = true;// Permets de sortir de la boucle en cas de défaite

                }
                System.out.print("La proposition de l'Ordi est  : " + tentativeOrdi + " -> Indication : ");
                ordiObj.compare(tentativeOrdi, codeUser);
                System.out.println();
                tentativeOrdi = ordiObj.newCodeOrdi(tentativeOrdi, codeUser);//Définit nouvelles combis Ordi baser sur la précédente
            }
        }while (!sortie);
        //Permet de rejouer même mode
        rejouer = menuObj.endMenuUser();
        if (rejouer == 1) duel(); //Condition pour relancer même mode
    }

}

