package com.menu;

import java.util.ResourceBundle;
import java.util.Scanner;

// Classe qui gère les paramètres
public class Parametres {
    private ResourceBundle properties = ResourceBundle.getBundle("params");
    private int toursTotale = Integer.parseInt(properties.getString("toursTotale"));
    private String min = properties.getString("minGenere");
    private String max = properties.getString("maxGenere");
    private int nombreUnit = Integer.parseInt(properties.getString("codeNumbers"));
    private boolean devMode = Boolean.parseBoolean(properties.getString("devMode"));

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