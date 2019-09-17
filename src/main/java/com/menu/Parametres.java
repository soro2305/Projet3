package com.menu;

import java.util.ResourceBundle;
import java.util.Scanner;

// Classe qui gère les paramètres
public class Parametres {
    ResourceBundle properties = ResourceBundle.getBundle("params");
    private int toursTotale = Integer.parseInt(properties.getString("toursTotale"));
    private int min = Integer.parseInt(properties.getString("minGenere"));
    private int max = Integer.parseInt(properties.getString("maxGenere"));
    private int nombreUnit = Integer.parseInt(properties.getString("codeNumbers"));
    boolean devMode = Boolean.parseBoolean(properties.getString("devMode"));

    public int getToursTotale() {
        return toursTotale;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
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