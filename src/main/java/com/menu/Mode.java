package com.menu;

import com.Main;
import com.joueur.Ordi;
import com.joueur.Player;
import com.joueur.User;
import org.apache.log4j.Logger;

import java.io.IOException;


/***
 * <b>La classe mode contient les modes de jeux a travers ces trois méthodes :</b>
 * <p>Chaque méthode correspond a un mode de jeu :</p>
 * <ul>
 *     <li>challenger()</li>
 *     <li>defenseur()</li>
 *     <li>duel()</li>
 * </ul>
 * @author Amet Soro
 * @version 1.0
 */
public class Mode {
    /**
     * Instanciation d'objet du type Logger servant a généré les logs dans Mode.
     */
    private static Logger logger = Logger.getLogger(Mode.class);
    /**
     * Instanciation d'objet du type Parametres servant a récupérer les paramètres du jeu.
     */
    private Parametres settingsObj;
    /**
     * Instanciation d'objet du type Player qui représente le joueur user.
     */
    private Player userObj;
    /**
     * Instanciation d'objet du type Player qui représente le joueur Ordi.
     */
    private Player ordiObj;
    /**
     * String contenant le code User.
     */
    private String codeUser;
    /**
     * String contenant le code Ordi.
     */
    private String codeOrdi;
    /**
     * boolean qui définis la condition pour rejouer.
     */
    private boolean rejouer;
    /**
     * int contenant le nombre de chiffres max du code.
     */
    private int nombreUnit;
    /**
     * String contenant la borne max du code a générer.
     */
    private String maxString;
    /**
     * String contenant la borne min du code a générer.
     */
    private String minString;
    /**
     * boolean qui définis la Validité du fichier properties.
     */
    private boolean isParamValid;
    /**
     * int contenant le nombre de tours max.
     */
    private int tours;

    /***
     * Constructeur par défaut de la classe Mode.
     */
    public Mode() {
        this.settingsObj = new Parametres();
        //  this.menuObj = new Menu();
        this.userObj = new User();
        this.ordiObj = new Ordi();
        this.rejouer = false;
        this.isParamValid = settingsObj.init();
        this.nombreUnit = settingsObj.getNombreUnit();
        this.maxString = settingsObj.getMax();
        this.minString = settingsObj.getMin();
        this.tours = settingsObj.getToursTotale();

    }

//Retour valeurs paramètres

    /***
     * Récupère le boolean isParamValid.
     * @return False si fichier properties introuvable.
     */
    public boolean isParamValid() {
        return isParamValid;
    }

    /**
     * Récupère le nombres de chiffres max de la combinaison.
     *
     * @return le nombres de chiffres max de la combinaison définit dans le fichier properties..
     */
    public int getNombreUnit() {
        return nombreUnit;
    }

    /**
     * Récupère la borne max de la combinaison.
     *
     * @return la borne max de la combinaison définit dans le fichier properties.
     */
    public String getMaxString() {
        return maxString;
    }

    /**
     * Récupère la borne min de la combinaison.
     *
     * @return la borne min de la combinaison définit dans le fichier properties.
     */
    public String getMinString() {
        return minString;
    }

    /**
     * Récupère le nombres de tours max de la combinaison.
     *
     * @return le nombres de tours max de la combinaison définit dans le fichier properties.
     */
    public int getTours() {
        return tours;
    }

    /***
     * Lance le mode challengeur qui consiste a trouver la combinaison de l'Ordi.
     * <p>L'instance de l'ordi ordiObj génère une combinaison grace à generCodeString().<br>
     * L'instance de l'User userObj défini une solution grace à defineCodeUser().<br>
     * L'Ordi compare les combinaisons grace a la méthode compare() et donne les indications -+=.<br>
     * Tans qu'il y a des manches et que l'user ne trouve pas la solution la boucle continue.</p>
     */
    public void challenger() {
        logger.info("Arriver dans le mode Challenger");
        //Initialisations des instances
        //conteurs for loop
        int i;
        int j;
        //Création combinaison EA
        codeOrdi = ordiObj.generCodeString(getMinString(), getMaxString());
        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous " + (getTours()) + " manches");
        System.out.println("            Il y aura " + getNombreUnit() + " chiffres par combinaison");
        if (settingsObj.isDevMode()) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //Boucle paramétre le nombre de tentative via la var "tours"
        for (i = 1; i <= tours; i++) {
            codeUser = userObj.defineCodeUser(false);
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

    }

    /***
     * Lance le mode defenseur l'Ordi doit trouver la combinaison de l'User.<br>
     * <p>L'instance de l'User userObj défini une combinaison grace à defineCodeUser().<br>
     * L'instance de l'ordi ordiObj génère la solution initiale grace à generCodeString(),<br>
     * en passant les paramètres borneMin et borneMax défini dans le fichier properties<br>
     * L'User compare les combinaisons grace a la méthode compare() et donne les indications -+=<br>
     * L'Ordi génère les solutions suivante en utilisant la méthode newCodeOrdi()<br>
     * qui retourne les nouvelle borneMin et borneMax<br>
     * Tans qu'il y a des manches et que l'Ordi ne trouve pas la solution la boucle continue</p>
     */
    public void defenseur() {
        logger.info("Arriver dans le mode Défenseur");
        String getmax = getMaxString();
        String getMin = getMinString();
        String borneMax = getmax;
        String borneMin = getMin;
        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("                 Il y aura en tous " + (getTours()) + " manches");
        System.out.println("            Il y aura " + getNombreUnit() + " chiffres par combinaison");
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser(true);
        //Définition 1 er combinaison Ordi
        codeOrdi = ordiObj.generCodeString(borneMin, borneMax);
        //Boucle paramètre le nombres de tentatives via la var int tours
        for (int i = 1; i <= getTours(); i++) {
            //Vérifie si les 2 combinaisons sont égales.Stop la partie si true
            if (codeOrdi.equals(codeUser)) {
                System.out.println("\n");
                System.out.println("GAME OVER l'Ordi a trouvé la solution  " + codeUser + " !!!\n\n ");//Conditions pour quitter la boucle si victoire de l'EA
                break;
            } else {
                if (settingsObj.isDevMode()) {
                    System.out.println("\"Mode Dev\" Borne min : " + borneMin + " Borne max : " + borneMax);
                }
                //Formate l affichage de sortie console
                System.out.print("Proposition : " + codeOrdi + " -> Réponse : ");
                //Méthode pour comparer les deux combinaisons et affiché "+-="
                ordiObj.compare(codeOrdi, codeUser);
                System.out.println("\n");
                // codeTempo = ordiObj.newCodeOrdi(codeOrdi, codeUser);

                String[] retour = ordiObj.newCodeOrdi(codeUser, codeOrdi, borneMin, borneMax);
                codeOrdi = retour[0];
                borneMin = retour[2];
                borneMax = retour[1];

                System.out.println("\n");
                //Affiche Game over quand nombres de tours épuisés
                if (i == getTours()) {
                    System.out.println("\n\n\n ============== L'ordi a épuisé toutes ses manches c'est GAGNÉ !!! ============== \n");
                }

            }
        }

    }

    /***
     * Lance le mode duel le premier a trouver la combinaison gagne.
     * <p>
     * L'instance de l'ordi ordiObj génère sa combinaison initiale grace à generCodeString().<br>
     * L'instance de l'ordi ordiObj génère une solution grace à generCodeString().<br>
     * L'instance de l'User userObj définit sa combinaison grace à defineCodeUser().<br>
     * L'instance de l'User userObj définit une solution grace à defineCodeUser().<br>
     * L'Ordi compare les combinaisons grace a la méthode compare() et donne les indications -+=.<br>
     * L'Ordi génère les solutions suivante en utilisant la méthode newCodeOrdi()<br>
     * qui retourne les nouvelle borneMin et borneMax.<br>
     * L'User continue de définir ses solutions en fonction des indications de l'Ordi.<br>
     * Tans que l'Ordi ou l'User ne trouve pas la solution la boucle continue.</p>
     */

    public void duel() {
        logger.info("Arriver dans le mode Duel");
        //Stock borne min max provisoir
        String getMax = getMaxString();
        String getMin = getMinString();
        String borneMax = getMax;
        String borneMin = getMin;
        String tentativeUser;
        String tentativeOrdi;
        boolean sortie = false;
        //Définition combinaison Ordi
        codeOrdi = ordiObj.generCodeString(getMinString(), getMaxString());
        //Définition tentative Ordi
        tentativeOrdi = ordiObj.generCodeString(getMinString(), getMaxString());

        //Infos qui indique  les valeurs en fonction des paramètres
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("                              Infos ");
        System.out.println("            Il y aura " + getNombreUnit() + " chiffres par combinaison");
        if (settingsObj.isDevMode()) System.out.println("             Mode Dev activé L'EA a choisie : " + codeOrdi);
        System.out.println("                        Bonnes chances !!!");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("========================================================================================================== ");
        System.out.println("==========================================================================================================");

        //Définition combinaison utilisateur
        codeUser = userObj.defineCodeUser(true);
        System.out.println("Votre combinaison est : " + codeUser + "\n");
        System.out.println("==========================================================================================================");

        //Do while loop pour continuer tant qu il n'y a pas de vainqueur
        do {
            System.out.println("==========================================================================================================");

            tentativeUser = userObj.defineCodeUser(false);
            if (tentativeUser.equals(codeOrdi)) {//compare si les réponses sont égales
                System.out.println("\n");
                System.out.println("Bravo la réponse était : " + codeOrdi + " c'est GAGNÉ !!!");
                sortie = true;// Permets de sortir de la boucle en cas de Victoire
            } else {
                System.out.print("Votre proposition est  " + tentativeUser + " -> Indication : ");
                ordiObj.compare(tentativeUser, codeOrdi);
                System.out.println();
                if (tentativeOrdi.equals(codeUser)) {//compare si les réponses sont égales
                    System.out.println("\n L'Ordi a trouvé : " + codeUser + " GAME OVER !!!");

                    sortie = true;// Permets de sortir de la boucle en cas de défaite
                    break;
                }
                if (settingsObj.isDevMode()) {
                    logger.info("             Mode Dev activé L'EA a choisie : " + codeOrdi + " Borne min : " + borneMin + " borne max : " + borneMax);
                }
                System.out.print("La proposition de l'Ordi est  : " + tentativeOrdi + " -> Indication : ");
                ordiObj.compare(tentativeOrdi, codeUser);
                System.out.println();
                //Définit nouvelles combis et nouveaux rangent Min max baser sur les indications
                String[] retourDuel = ordiObj.newCodeOrdi(codeUser, tentativeOrdi, borneMin, borneMax);
                //Stockage des réponses
                tentativeOrdi = retourDuel[0];
                borneMin = retourDuel[2];
                borneMax = retourDuel[1];
                System.out.println("==========================================================================================================");

            }

        } while (!sortie);


    }

}

