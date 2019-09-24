package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;

//Classe User regroupe les fonctionnalités propres à l'User, essentiellement les saisis claviers
public class User extends Player {
    //Initialisation du logger pour user
    private static Logger logger = Logger.getLogger(User.class);
    Parametres nombreUnit = new Parametres();
    String saisie = "";

    //Méthode qui permet à l'utilisateur d'entrée sa combinaison
    public String defineCodeUser(boolean defineOrAttempt) {

        int saisieExeption;
        boolean nombreChiffre = false;
        boolean chiffreExeption = false;
        System.out.println(chiffreExeption);

        int tourRestants = 1; // Variable int qui indiquent le nombre d'essai restant
        // Boucle qui tourne tant que la réponse ne correspond pas au critère établi dans le fichier properties
        do {
            if (defineOrAttempt == true) {
                System.out.println("Veuillez définir une combinaison à " + nombreUnit.getNombreUnit() + " chiffres que l'Ordi doit deviner !!!");
            } else {
                System.out.println("Deviner la combinaison secrète de l'Ordi. C'est à vous de jouer !!! ");
            }
            this.saisie = saisieUser.nextLine();// Retourne saisie du Scanner
            try {
                saisieExeption = Integer.parseInt(this.saisie);
            } catch (NumberFormatException e) {
                logger.error("Entrer invalide !!! La saisie ne doit comporter que des chiffre compris entre 0 et 9 !!!");
                chiffreExeption = true;
                System.out.println(chiffreExeption);

            }
            String unitLength = nombreUnit.getMax();//Variable qui vérifie la longueur de la combinaison dans les paramètres
            if (saisie.length() == unitLength.length()) {//Comparaison pour voir si les valeurs corresponde
                nombreChiffre = true;//Condition pour sortir de la boucle modifiée
                tourRestants = 1;//Valeur du compteur reinitisialiser
            } else {
                logger.error("Entrer invalide !!! \nLa combinaison ne peut être composé que de " + nombreUnit.getNombreUnit() + " chiffres uniquement !!!");
                if (tourRestants != 4) {//Indique le nombre d'essais restants
                    System.out.println("Il vous reste " + (4 - tourRestants) + " essaies");
                } else {
                    System.out.println("Vous avez épuisé toutes vos tentatives à bientôt ");
                    System.exit(-1);//Indique la sortie
                }
            }
            tourRestants++;//Incrémentations du conteur d'essai
            System.out.println(chiffreExeption);

        } while ((nombreChiffre == false) || (chiffreExeption == true));//Condition pour sortir de la boucle
        return this.saisie;//Retour d'une valeur correct
    }
}

//    //Méthode qui permet à l'utilisateur d'entrée une tentative de devinette
//    public String attemptUser() {
//        boolean nombreChiffre = false;
//        boolean chiffreExeption = false;
//        int i = 1;// Conteurs qui indiquent le nombre d'essai restant
//        do {
//            System.out.println("Deviner la combinaison secrète de l'Ordi. C'est à vous de jouer !!! ");
//            try {
//                this.saisie = saisieUser.nextLine();// Retourne saisie du Scanner
//            } catch (NumberFormatException e) {
//                logger.error("La saisie ne doit comporter que des chiffre entre compris entre 0 et 9 ");
//                chiffreExeption = true;
//            }
//            String unitLength = nombreUnit.getMax();//Variable qui vérifie la longueur de la combinaison dans le fichier properties
//            if (saisie.length() == unitLength.length()) {//Comparaison pour voir si les valeurs corresponde
//                nombreChiffre = true;//Condition pour sortir de la boucle modifiée
//                i = 1;//Valeur du compteur reinitisialiser
//            } else {
//                logger.error("Entrer invalide !!! \nLa combinaison ne peut être composé que de " + nombreUnit.getNombreUnit() + " chiffres uniquement");
//                if (i != 4) {//Indique le nombre d'essais restants
//                    System.out.println("Il vous reste " + (4 - i) + " essaies");
//                } else {
//                    System.out.println("Vous avez épuisé toutes vos tentatives à bientôt ");
//                    System.exit(-1);//Indique la sortie
//                }
//            }
//            i++;//Incrémentations du conteur d'essai
//        } while ((nombreChiffre == false) && (chiffreExeption == false));//Condition pour sortir de la boucle
//        return this.saisie;//Retour d'une valeur correct
//    }
//
//}

