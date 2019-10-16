package com.menu;

import org.apache.log4j.Logger;
import java.io.IOException;
import java.util.ResourceBundle;



/**
 * Gère les paramètres du jeu définit dans le fichier properties
 */
public class Parametres {
    //Initialisation du logger paramètres
    private static Logger logger = Logger.getLogger(Parametres.class);
    // Déclarations des instances qui vont contenir les valeurs du fichier properties
    private ResourceBundle properties;
    //   ResourceBundle properties = ResourceBundle.getBundle("params");
    private int toursTotale;
    private String min;
    private String max;
    private int nombreUnit;
    private boolean devMode;




    /**
     * Constructeur paramètre initialise des valeurs par défaut en cas d'erreur
     */
    public Parametres() {
        this.properties = null;
        this.toursTotale = 4;
        this.min = "0000";
        this.max = "9999";
        this.nombreUnit = 4;
        this.devMode = false;
    }

    /**
     * Contient les try catch pour gérer les exceptions, relatives à la validité des valeurs, du fichier properties
     * @return true si aucune erreur détéctée
     * @throws IOException
     */
    public boolean init() throws IOException {
        //Il faut commenter une des deux lignes selon que l'on soit sur l'ide ou que l'on utilise le jar
        //fonctionne a partir de l ide
        this.properties = ResourceBundle.getBundle("params");
        //fonctionne hors ide
        //this.properties = new PropertyResourceBundle(Files.newInputStream(Paths.get("params.properties")));

        try {
            this.toursTotale = Integer.parseInt(properties.getString("toursTotale"));
        } catch (NumberFormatException e) {
            logger.error("Le paramètre toursTotale dans properties n'équivaut pas à un nombre. Il y aura 4 tours par défaut");
        }

        try {
            this.min = properties.getString("minGenere");
            Integer.parseInt(this.min);
        } catch (NumberFormatException e) {
            logger.error("Le paramètre 'minGenere' dans properties n'est pas un nombre. '0000' a étais affecter par défaut");
        }
        try {
            this.max = properties.getString("maxGenere");
            Integer.parseInt(this.max);
        } catch (NumberFormatException e) {
            logger.error("Le paramètres 'maxGenere' dans properties n'est pas un nombre.'9999' a étais affecter par défaut");
        }

        try {
            this.nombreUnit = Integer.parseInt(properties.getString("codeNumbers"));
        } catch (NumberFormatException e) {
            logger.error("Le paramètres nombreUnit dans properties n'equivaut pas a un nombre. La valeurs de 4 lui a étais affecter par default");
        }
        this.devMode = Boolean.parseBoolean(properties.getString("devMode"));


        return true;
    }



    /**
     * Récupére la valeur ToursTotale initialiser dans le fichier properties
     * @return le nombre de tours totale de la partie
     */
    public int getToursTotale() {
        return toursTotale;
    }
    /**
     * Récupére la valeur min initialiser dans le fichier properties
     * @return la valeur de la borne min du générateur
     */
    public String getMin() {
        return min;
    }
    /**
     * Récupére la valeur max initialiser dans le fichier properties
     * @return la valeur de la borne max du générateur
     */
    public String getMax() {
        return max;
    }
    /**
     * Récupére la valeur true ou false du mode dev dans le fichier properties
     * @return la valeur du mode dev
     */
    public boolean isDevMode() {
        return devMode;
    }
    /**
     * Récupére la valeur nombreUnit initialiser dans le fichier properties
     * @return le nombre de chiffres de la combinaison
     */
    public int getNombreUnit() {
        return nombreUnit;
    }
}
