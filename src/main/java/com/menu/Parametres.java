package com.menu;

import com.Main;
import org.apache.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;

// Classe qui gère les paramètres
public class Parametres {

    private static Logger logger = Logger.getLogger(Parametres.class);

    private ResourceBundle properties;
    private int toursTotale;
    private String min ;
    private String max ;
    private int nombreUnit ;
    private boolean devMode ;



    public Parametres() {
        try {
            this.properties = ResourceBundle.getBundle("params");
        } catch (MissingResourceException e){
            logger.error("Le fichier properties est introuvable ");
            e.printStackTrace();
            System.exit(-1);
        }
        try {
            this.toursTotale = Integer.parseInt(properties.getString("toursTotale"));
        } catch (NumberFormatException e){
            logger.error("Le paramètres toursTotale dans properties n'equivaut pas a un nombre. La valeurs par défault de 10 a étais mis a la place");
            this.toursTotale = 10;
        }
        try {
            this.min = properties.getString("minGenere");
        } catch (NumberFormatException e){
            logger.error("Le paramètres 'minGenere' dans properties n'est pas un nombre. La valeurs par défault de '0000' a étais attribuer a min a la place");
            this.min = "0000";
        }
        try {
            this.max = properties.getString("maxGenere");
        } catch (NumberFormatException e){
            logger.error("Le paramètres 'minGenere' dans properties n'est pas un nombre. La de '9999' a étais affecter a 'max' a la place");
            this.max = "9999";
        }

        try {
        this.nombreUnit = Integer.parseInt(properties.getString("codeNumbers"));
    } catch (NumberFormatException e) {
            logger.error("Le paramètres nombreUnit dans properties n'equivaut pas a un nombre. La valeurs de 4 lui a étais affecter par default");
            this.nombreUnit = 4;
        }
        this.devMode = Boolean.parseBoolean(properties.getString("devMode"));
    }

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


    public void settings() {

    }

    public int getNombreUnit() {
        return nombreUnit;
    }
}