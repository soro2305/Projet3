package com.menu;

import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

// Classe qui gère les paramètres
public class Parametres {
    //Initialisation du logger paramètres
    private static Logger logger = Logger.getLogger(Parametres.class);
    // Déclarations des instances qui vont contenir les valeurs du fichier properties
    private Properties properties ;
 //   private ResourceBundle properties;
    private int toursTotale;
    private String min;
    private String max;
    private int nombreUnit;
    private boolean devMode;


    //Constructeur paramètre initialise des valeurs par défaut en cas d'erreur
    public Parametres() {
        this.properties = null;
        this.toursTotale = 4;
        this.min = "0000";
        this.max = "9999";
        this.nombreUnit = 4;
        this.devMode = false;
    }
    //Contient les try catch pour gérer les exceptions, relatives à la validité des valeurs, du fichier properties

    public boolean init() {

        InputStream input;
        try {
            this.properties = new Properties();
            input = new FileInputStream("params.properties");
            properties.load(input);
        } catch (IOException e) {
            return false;
        }

        try {
            this.toursTotale = Integer.parseInt(properties.getProperty("toursTotale"));
        } catch (NumberFormatException e) {
            logger.error("Le paramètre toursTotale dans properties n'équivaut pas à un nombre. Il y aura 4 tours par défaut");
        }

        try {
            this.min = properties.getProperty("minGenere");
             Integer.parseInt(this.min);
        } catch (NumberFormatException e) {
            logger.error("Le paramètre 'minGenere' dans properties n'est pas un nombre. '0000' a étais affecter par défaut");
        }
        try {
            this.max = properties.getProperty("maxGenere");
            Integer.parseInt(this.max);
        } catch (NumberFormatException e) {
            logger.error("Le paramètres 'maxGenere' dans properties n'est pas un nombre.'9999' a étais affecter par défaut");
        }

        try {
            this.nombreUnit = Integer.parseInt(properties.getProperty("codeNumbers"));
        } catch (NumberFormatException e) {
            logger.error("Le paramètres nombreUnit dans properties n'equivaut pas a un nombre. La valeurs de 4 lui a étais affecter par default");
        }
        this.devMode = Boolean.parseBoolean(properties.getProperty("devMode"));


      return true;
    }


    //Getter des instances paramètres
    public int getToursTotale() {
        return toursTotale;
    }

    public String getMin() {
        return min;
    }

    public String getMax() {
        return max;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public int getNombreUnit() {
        return nombreUnit;
    }
}
