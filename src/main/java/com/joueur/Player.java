package com.joueur;

import com.menu.Parametres;
import org.apache.log4j.Logger;

import java.util.Random;
import java.util.Scanner;

/**
 * Classe parent des classes Ordi et User elle contient toutes les méthodes de ces dernieres
 */
public class Player {
    //Initialisation des instances
    protected static Logger logger = Logger.getLogger(Player.class);
    Random genere = new Random();
    Scanner saisieUser = new Scanner(System.in);

    /**
     * Compare les combinaisons et affiche les indications à travers les signes =-+
     * @param codeUser combinaison de l'utilisateur
     * @param codeOrdi combinaison de l'ordi
     */
    public void compare(String codeUser, String codeOrdi) {

        try {//Handle L'exception OutOfBounds si mauvais paramétrage du fichier properties
            //Deux boucles pour convertir chaque caractère des args en int.
            for (int i = 0; i < codeOrdi.length(); i++) {
                for (int i2 = 0; i < codeUser.length(); i++) {

                    int b = Character.getNumericValue(codeUser.charAt(i));
                    int a = Character.getNumericValue(codeOrdi.charAt(i));

                    //Conditions pour comparer et définir l'indication à retourner
                    if (a < b) {
                        System.out.print("-");
                    }
                    if (a > b) {
                        System.out.print("+");
                    }
                    if (a == b) {
                        System.out.print("=");
                    }
                }
            }
        } catch (StringIndexOutOfBoundsException e) {//Handle L'exception OutOfBounds si mauvais paramétrage du fichier properties
            System.out.println();
            logger.error("Le parametres properties 'minGenere' dois etre égale en nombres de chiffres au parametres 'maxGenere'");
            logger.error("Le nombres de chiffres du paramètre properties 'maxGenere' doit correspondre au montant du paramètres 'codeNumbers'");
            System.exit(-1);//Quitte l'application en conséquence de l'exception

        }
    }



    /**
     * Génère un code aléatoire String entre les bornes minimum et maximum
     * @param min borne minimum
     * @param max borne maximum
     * @return nouveau chiffres générer
     */
    public String generCodeString(String min, String max) {
        int minI = Integer.parseInt(min);
        int maxI = Integer.parseInt(max);
        int stockReponse = genere.nextInt((maxI - minI) + 1) + minI; //Classe Random utiliser pour définir les bornes max min de la combinaison
        String newCode = Integer.toString(stockReponse); //Conversion de la combi en string
        return newCode; //Retour combinaison String
    }

    /**
     * Génère un code aléatoire int entre les bornes minimum et maximum
     * @param min borne minimum
     * @param max borne maximum
     * @return nouveau chiffres int générer
     */
    public int generCodeInt(int min, int max) {
        int newsUnit = genere.nextInt((max - min) + 1) + min; //Classe Random utiliser pour définir les bornes max min de la combinaison
        return newsUnit; //Retour combinaison
    }


    /**
     * Définit la combinaison de l'utilisateur
     * @param defineOrAttempt Si true, définit sa propre combinaison. Si false, devine la combinaison de l'ordi
     * @return La combinaison saisie
     */
    public String defineCodeUser(boolean defineOrAttempt) {
        System.out.println("Veuillez définir une combinaison que l'Ordi doit deviner !!!");
        return saisieUser.nextLine();// Retourne saisie du Scanner
    }


    /**
     * Permet à l'ordi de générer une combinaison  à partir des indications de sa précédente combinaison
     * @param codeOrdi1 Combinaison générer par l'ordi
     * @param codeUser Combinaison générer par l'user
     * @param min1 Précédente borne minimum
     * @param max1 Précédente borne maximum
     * @return Array Tableau comprenant nouvelle combinaison ordi ainsi que nouvelle borne Min et Max
     */
    public String[] newCodeOrdi(String codeOrdi1, String codeUser, String min1, String max1) {
        //Initialisation Objets variables
        Parametres settingsObj = new Parametres();
        Player playerObj = new Player();
        int unitPlusHaut = 0;
        int unitPlusBas = 0;
        String[] retour = new String[2];

        int nombreUnit = settingsObj.getNombreUnit();//Définit le nombre de chiffres combi selon les réglages paramètres
        //Array pr stocker current en int et combi final en String
        int[] stockCode = new int[nombreUnit];//Stocke news code int

        String newCode[] = new String[stockCode.length];//Stocke news code int en String
        //Reponse final format String
        String newCodeString = "";
        //Deux boucles pour séparés caractères des code Ordi et User
        for (int i = 0; i < codeOrdi1.length(); i++) {
            for (int i2 = 0; i < codeUser.length(); i++) {
                //Stockage de ses caractères dans des var int
                int unitCodeUser = Character.getNumericValue(codeUser.charAt(i));
                int unitCodeOrdi = Character.getNumericValue(codeOrdi1.charAt(i));
                //Conditions pour comparer si les futures unités combi Ordi doivent êtres "-+="
                if (unitCodeUser < unitCodeOrdi) {
                    //Boucle génére nouvelle unité tant que plus haute que précédente
                    do {
                        //Méthode random génére news units.Borne max ordi -1 pr évité les doublons
                        unitPlusBas = playerObj.generCodeInt(1, (unitCodeOrdi - 1));
                    } while (unitPlusBas > unitCodeOrdi);
                    stockCode[i] = unitPlusBas;//Stock new code
                }
                //Boucle génére nouvelle unité tant que plus basse que précédente
                if (unitCodeUser > unitCodeOrdi) {
                    do {
                        //Méthode random génére news units.Borne min ordi +1 pr évité les doublons
                        unitPlusHaut = playerObj.generCodeInt((unitCodeOrdi + 1), unitCodeUser);
                    } while (unitPlusHaut < unitCodeOrdi);
                    stockCode[i] = unitPlusHaut;
                }
                //Génére unité égales
                if (unitCodeUser == unitCodeOrdi) {
                    stockCode[i] = playerObj.generCodeInt(unitCodeOrdi, unitCodeOrdi);
                }
            }
        }

        //   Transforme int array en string array
        for (int j = 0; j < stockCode.length; j++) {
            newCode[j] = String.valueOf(stockCode[j]);
        }
        //Transforme array String en String
        newCodeString = Player.ArrayToString(newCode);
        //retour nouvelle combinaison en String
        return retour;
    }


    /**
     * Converti un Array de String en String
     * @param strArray array de string a convertir en string
     * @return nouveau string
     */
    public static String ArrayToString(String[] strArray) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < strArray.length; i++) {
            stringBuilder.append(strArray[i]);
        }
        return stringBuilder.toString();
    }
}

