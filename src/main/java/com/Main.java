package com;

import com.menu.Menu;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * <b>Contient la méthode main qui est le point de départ de l'application.</b>
 */
public class Main {
    /**
     * Instanciation d'objet du type Logger servant a généré les logs dans Main.
     *
     * @author Amet Soro
     * @version 1.0
     */
    private static Logger logger = Logger.getLogger(Main.class);
    /**
     * Instanciation d'objet du type Menu servant a diriger vers le menu principal.
     */
    private static Menu menuObj = new Menu();

    /**
     * <p>Tous le flow du programme s'exécute dans cette méthode.</p>
     *
     * @param args Contient "command-line arguments".
     */
    public static void main(String[] args) {

        try {//Try Cath englobe  toute exception comprise dans l'application
            logger.info("this is information message");
            System.out.println("\n ============================= Bienvenue dans Guess My Code !!! ========================== \n");
            if (menuObj.startMenuUser()) {//Envoie au menu principal
                System.out.println("Merci à bientôt !");//Message perçu quand on quitte l'application
            }
            System.out.println();
        } catch (Exception e) {//Catch au plus haut niveau les exceptions comprises dans l'application
            System.out.println("\"Une erreur inconnue est survenue veuillez contacter votre admin");
            logger.error("Une erreur inconnue est survenue (" + e.getClass() + ")");//Affiche la class de l'exception
            e.printStackTrace();//Affiche le StackTrace de l'exception
        }
    }

    /**
     * Récupère l'objet menuObj.
     *
     * @return L'objet menuObj.
     */
    public static Menu getMenuObj() {
        return menuObj;
    }

}
