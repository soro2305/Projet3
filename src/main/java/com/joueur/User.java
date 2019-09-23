package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;

//Classe User regroupe les fonctionnalités propres à l'User, essentiellement les saisis claviers
public class User extends Player {
    private static Logger logger = Logger.getLogger(User.class);
    Parametres nombreUnit = new Parametres();
    String saisie = "";

    //Méthode qui permet à l'utilisateur d'entrée sa combinaison
    public String defineCodeUser() {
        boolean sortieBoucle = false;
        int i = 1; // Variable int qui indiquent le nombre d'essai restant
        // Boucle qui tourne tant que la réponse ne correspond pas au critère établi dans le fichier properties
        do {
            System.out.println("Veuillez définir une combinaison à " + nombreUnit.getNombreUnit() + " chiffres que l'Ordi doit deviner !!!");
            this.saisie = saisieUser.nextLine();// Retourne saisie du Scanner

            String unitLength = nombreUnit.getMax();//Variable qui vérifie la longueur de la combinaison dans le fichier properties
            if (saisie.length() == unitLength.length()) {//Comparaison pour voir si les valeurs corresponde
                sortieBoucle = true;//Condition pour sortir de la boucle modifiée
                i = 1;//Valeur du compteur reinitisialiser
            } else {
                logger.error("Entrer invalide !!! \nLa combinaison ne peut être composé que de " + nombreUnit.getNombreUnit() + " chiffres uniquement");
                if (i != 4) {//Indique le nombre d'essais restants
                    System.out.println("Il vous reste " + (4 - i) + " essaies");
                } else {
                    System.out.println("Vous avez épuisé toutes vos tentatives à bientôt ");
                    System.exit(-1);//Indique la sortie
                }
            }
            i++;//Incrémentations du conteur d'essai
        } while (sortieBoucle == false);//Condition pour sortir de la boucle
        return this.saisie;//Retour d'une valeur correct
    }


    //Méthode qui permet à l'utilisateur d'entrée une tentative de devinette
    public String attemptUser() {
        boolean sortieBoucle = false;
        int i = 1;// Conteurs qui indiquent le nombre d'essai restant
        do {
            System.out.println("Deviner la combinaison secrète de l'Ordi. C'est à vous de jouer !!! ");
            this.saisie = saisieUser.nextLine();// Retourne saisie du Scanner

            String unitLength = nombreUnit.getMax();//Variable qui vérifie la longueur de la combinaison dans le fichier properties
            if (saisie.length() == unitLength.length()) {//Comparaison pour voir si les valeurs corresponde
                sortieBoucle = true;//Condition pour sortir de la boucle modifiée
                i = 1;//Valeur du compteur reinitisialiser
            } else {
                logger.error("Entrer invalide !!! \nLa combinaison ne peut être composé que de " + nombreUnit.getNombreUnit() + " chiffres uniquement");
                if (i != 4) {//Indique le nombre d'essais restants
                    System.out.println("Il vous reste " + (4 - i) + " essaies");
                } else {
                    System.out.println("Vous avez épuisé toutes vos tentatives à bientôt ");
                    System.exit(-1);//Indique la sortie
                }
            }
            i++;//Incrémentations du conteur d'essai
        } while (sortieBoucle == false);//Condition pour sortir de la boucle
        return this.saisie;//Retour d'une valeur correct
    }

}

