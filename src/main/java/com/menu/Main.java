package com.menu;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {
//  private Logger logger = LogManager.getLogger(App.class);
    public static void main(String[] args) {
        System.out.println("\n ============================= Bienvenue dans Guess My Code !!! ========================== \n");
        Menu start = new Menu();
        start.startMenuUser();
        System.out.println("Merci à bientôt !");
    }
}
