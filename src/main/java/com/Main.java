package com;

import com.menu.Menu;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        logger.setLevel(Level.ALL);
        logger.info("this is information message");
        System.out.println("\n ============================= Bienvenue dans Guess My Code !!! ========================== \n");
        Menu start = new Menu();
        start.startMenuUser();
        System.out.println("Merci à bientôt !");
        System.out.println();
    }
}
