package com;

import com.menu.Menu;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {
    //Initialisation du logger pour main
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {//Try Cath englobe  toute exception comprise dans l'application
            DOMConfigurator.configure("log4j.xml");//Envoie la configue sur le fichier log4j.xml
            logger.info("this is information message");
            System.out.println("\n ============================= Bienvenue dans Guess My Code !!! ========================== \n");
            Menu start = new Menu();
            if (start.startMenuUser()){//Envoie au menu principal
            System.out.println("Merci à bientôt !");//Message perçu quand on quitte l'application
            }
            System.out.println();
        } catch (Exception e) {//Catch au plus haut niveau les exceptions comprises dans l'application
            System.out.println("\"Une erreur inconnue est survenue veuillez contacter votre admin");
            logger.error("Une erreur inconnue est survenue (" + e.getClass() + ")");//Affiche la class de l'exception
            e.printStackTrace();//Affiche le StackTrace de l'exception
        }
    }
}
