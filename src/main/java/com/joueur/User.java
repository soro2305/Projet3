package com.joueur;

public class User extends Player {
    //Méthode qui permet à l'utilisateur d'entrée sa combinaison
    public String defineCodeUser() {
        System.out.println("Veuillez définir une combinaison que l'Ordi doit deviner !!!");
        return saisieUser.nextLine();// Retourne saisie du Scanner
    }

    //Méthode qui permet à l'utilisateur d'entrée une tentative de devinette
    public String attemptUser() {
        System.out.println("Deviner la combinaison secrète de l'Ordi. C'est à vous de jouer !!! ");
        return saisieUser.nextLine();// Retourne saisie du Scanner
    }
}
