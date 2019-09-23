package com;

import com.menu.Menu;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        try {
            DOMConfigurator.configure("resources/log4j.xml");
            logger.info("this is information message");
            System.out.println("\n ============================= Bienvenue dans Guess My Code !!! ========================== \n");
            Menu start = new Menu();
            start.startMenuUser();
            System.out.println("Merci à bientôt !");
            System.out.println();
        }catch (Exception e){
            System.out.println("");
            logger.error("Une erreur inconnue est survenue ("+e.getClass()+")");
            e.printStackTrace();
        }
    }
}
