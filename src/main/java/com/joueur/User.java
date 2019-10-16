package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;


/**
 * Regroupe les fonctionnalités propres à l'User, les saisis claviers
 */
public class User extends Player {
    //Initialisation du logger pour user
    Parametres nombreUnit = new Parametres();
    String saisie = "";

    /**
     * Définit la combinaison de l'utilisateur
     * @param defineOrAttempt Si true, définit sa propre combinaison. Si false, devine la combinaison de l'ordi
     * @return La combinaison saisie
     */    public String defineCodeUser(boolean defineOrAttempt) {

        boolean nombreChiffre = false;
        boolean chiffreExeption ;

        int tourRestants = 1; // Variable int qui indiquent le nombre d'essai restant
        // Boucle qui tourne tant que la réponse ne correspond pas au critère établi dans le fichier properties
        do {

            if (defineOrAttempt == true) {
                System.out.println("Veuillez définir une combinaison à " + nombreUnit.getNombreUnit() + " chiffres que l'Ordi doit deviner !!!");
            } else {
                System.out.println("Deviner la combinaison secrète de l'Ordi. C'est à vous de jouer !!! ");
            }
            this.saisie = saisieUser.nextLine();// Retourne saisie du Scanner
             chiffreExeption = false;

            try {
                Integer.parseInt(this.saisie);
            } catch (NumberFormatException e) {
                System.out.println("Entrer invalide !!! La saisie ne doit comporter que "+nombreUnit.getNombreUnit()+" chiffre compris entre 0 et 9 !!!");
                chiffreExeption = true;

            }

            String unitLength = nombreUnit.getMax();//Variable qui vérifie la longueur de la combinaison dans les paramètres
            if (saisie.length() == unitLength.length()) {//Comparaison pour voir si les valeurs corresponde
                nombreChiffre = true;
            }else if (chiffreExeption==false){
                System.out.println("Entrer invalide !!! La saisie ne doit comporter que "+nombreUnit.getNombreUnit()+" chiffre compris entre 0 et 9 !!!");
            }

        } while ((nombreChiffre == false) || (chiffreExeption == true));//Condition pour sortir de la boucle
        return this.saisie;//Retour d'une valeur correct
    }
}


